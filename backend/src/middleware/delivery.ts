import { NextFunction, Request, Response } from 'express'
import { Delivery, DeliveryStatus } from '../model/Delivery'
import { TransportRequest, TransportRequestStatus } from '../model/TransportRequest'

export const createDelivery = async (req: Request, res: Response, next: NextFunction) => {
  const delivery = Delivery.build({
    ...req.body,
    clientUser: res.locals.dbUser?._id,
    status: DeliveryStatus.UNASSIGNED
  })

  await delivery.save()
  return res.status(201).send(delivery)
}

export const updateDelivery = async (req: Request, res: Response, next: NextFunction) => {
  if (res.locals.dbUser?._id.toString() !== res.locals.delivery?.clientUser.toString() && !res.locals.dbUser?.isAdmin) {
    return res.sendStatus(403)
  }
  const delivery = await Delivery.findOneAndUpdate({ _id: req.params.id }, { ...req.body }).exec()

  if (delivery == null) {
    return res.sendStatus(404)
  }
  return res.status(200).send(delivery)
}

export const returnDelivery = async (req: Request, res: Response, next: NextFunction) => {
  return res.status(200).send(res.locals.delivery)
}

export const readDelivery = async (req: Request, res: Response, next: NextFunction) => {
  const delivery = await Delivery.findById(req.params.id).exec()
  if (delivery == null) {
    return res.sendStatus(404)
  }
  res.locals.delivery = delivery
  return next()
}

export const statusChangeMiddleware = async (req: Request, res: Response, next: NextFunction) => {
  if (res.locals.delivery?.status !== DeliveryStatus.ASSIGNED &&
    res.locals.delivery?.status !== DeliveryStatus.IN_TRANSIT) {
    return res.sendStatus(400)
  }
  if (res.locals.dbUser?._id.toString() !== res.locals.delivery?.transporterUser?.toString()) {
    return res.sendStatus(403)
  }

  res.locals.delivery.status = req.body.status
  res.locals.delivery.save()
  return res.status(200).send(res.locals.delivery)
}

export const rateClientMiddleware = async (req: Request, res: Response, next: NextFunction) => {
  if (res.locals.dbUser?._id.toString() !== res.locals.delivery?.transporterUser?.toString()) {
    return res.sendStatus(403)
  }

  if (res.locals.delivery == null) {
    return res.sendStatus(404)
  }

  if (res.locals.delivery.status === DeliveryStatus.UNASSIGNED ||
    res.locals.delivery.status === DeliveryStatus.PENDING) {
    return res.sendStatus(403)
  }

  res.locals.delivery.clientRating = req.body.rating
  res.locals.delivery.save()
  return res.status(200).send(res.locals.delivery)
}

export const rateTransporterMiddleware = async (req: Request, res: Response, next: NextFunction) => {
  if (res.locals.dbUser?._id.toString() !== res.locals.delivery?.clientUser?.toString()) {
    return res.sendStatus(403)
  }

  if (res.locals.delivery == null) {
    return res.sendStatus(404)
  }

  if (res.locals.delivery.status === DeliveryStatus.UNASSIGNED ||
    res.locals.delivery.status === DeliveryStatus.PENDING) {
    return res.sendStatus(403)
  }

  res.locals.delivery.transporterRating = req.body.rating
  res.locals.delivery.save()
  return res.status(200).send(res.locals.delivery)
}

export const addRequestMiddleware = async (req: Request, res: Response, next: NextFunction) => {
  if (res.locals.dbUser?._id.toString() === res.locals.delivery?.clientUser?.toString()) {
    return res.sendStatus(403)
  }

  if (res.locals.delivery == null) {
    return res.sendStatus(404)
  }
  if (res.locals.delivery.status !== DeliveryStatus.UNASSIGNED &&
    res.locals.delivery.status !== DeliveryStatus.PENDING) {
    return res.sendStatus(403)
  }

  const request = TransportRequest.build({
    user: res.locals.dbUser?._id,
    delivery: res.locals.delivery._id,
    status: TransportRequestStatus.PENDING
  })
  await request.save()
  if (res.locals.delivery.status === DeliveryStatus.UNASSIGNED) {
    res.locals.delivery.status = DeliveryStatus.PENDING
    await res.locals.delivery.save()
  }

  return res.status(201).send(request)
}

export const replyMiddleware = async (req: Request, res: Response, next: NextFunction) => {
  if (res.locals.dbUser?._id.toString() !== res.locals.delivery?.clientUser?.toString()) {
    return res.sendStatus(403)
  }

  const request = await TransportRequest.findOne({ user: req.body.userId, delivery: res.locals.delivery?._id }).exec()
  if (request === null) {
    return res.sendStatus(404)
  }

  if (req.body.accept) {
    request.status = TransportRequestStatus.ACCEPTED
    res.locals.delivery!!.status = DeliveryStatus.ASSIGNED
    res.locals.delivery!!.transporterUser = req.body.userId
    await Promise.all([request.save(), res.locals.delivery!!.save()])
  } else {
    request.status = TransportRequestStatus.REJECTED
    await request.save()
  }

  return res.status(200).send(request)
}

export const deleteDelivery = async (req: Request, res: Response) => {
  if (res.locals.dbUser?._id.toString() !== res.locals.delivery?.clientUser.toString() && !res.locals.dbUser?.isAdmin) {
    return res.sendStatus(403)
  }

  await TransportRequest.deleteMany({ delivery: res.locals.delivery?._id })
  await Delivery.deleteOne({ _id: res.locals.delivery?._id })
  return res.status(200).send(res.locals.delivery)
}

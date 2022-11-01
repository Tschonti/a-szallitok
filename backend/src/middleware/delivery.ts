import { NextFunction, Request, Response } from 'express'
import { Delivery, DeliveryStatus } from '../model/Delivery'

export const createDelivery = async (req: Request, res: Response, next: NextFunction) => {
  const delivery = Delivery.build({
    ...req.body,
    clientUser: res.locals.dbUser?._id,
    status: DeliveryStatus.UNASSIGNED
  })

  await delivery.save()
  return res.status(201).send(delivery)
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

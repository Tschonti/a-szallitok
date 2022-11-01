/* eslint-disable @typescript-eslint/no-extra-non-null-assertion */
import { NextFunction, Request, Response } from 'express'
import { Delivery, DeliveryStatus } from '../model/Delivery'
import { Location } from '../model/Location'

export const createDelivery = async (req: Request, res: Response, next: NextFunction) => {
  const delivery = Delivery.build({
    title: req.body.title,
    description: req.body.description,
    sourceLocation: res.locals.sourceLocation?._id,
    destinationLocation: res.locals.destinationLocation?._id,
    clientUser: res.locals.dbUser?._id,
    pickUpFrom: req.body.pickUpFrom,
    pickUpUntil: req.body.pickUpUntil,
    price: req.body.price,
    capacity: {
      weight: req.body.weight,
      height: req.body.height,
      length: req.body.length,
      width: req.body.width
    },
    pictureUrl: req.body.pictureUrl,
    status: DeliveryStatus.UNASSIGNED
  })

  await delivery.save()
  return res.status(201).send(delivery)
}

export const createLocationEntities = async (req: Request, res: Response, next: NextFunction) => {
  const sourceLocation = Location.build({
    coordinate: {
      longitude: req.body.sourceLocation.longitude,
      latitude: req.body.sourceLocation.latitude
    },
    country: req.body.sourceLocation.country,
    postalCode: req.body.sourceLocation.postalCode,
    city: req.body.sourceLocation.city,
    address: req.body.sourceLocation.address
  })
  const destinationLocation = Location.build({
    coordinate: {
      longitude: req.body.destinationLocation.longitude,
      latitude: req.body.destinationLocation.latitude
    },
    country: req.body.destinationLocation.country,
    postalCode: req.body.destinationLocation.postalCode,
    city: req.body.destinationLocation.city,
    address: req.body.destinationLocation.address
  })
  await sourceLocation.save()
  await destinationLocation.save()
  res.locals.sourceLocation = sourceLocation
  res.locals.destinationLocation = destinationLocation
  return next()
}

export const getDelivery = async (req: Request, res: Response, next: NextFunction) => {
  const delivery = await Delivery.findById(req.params.id).populate(['sourceLocation', 'destinationLocation']).exec()
  if (delivery == null) {
    return res.sendStatus(404)
  }
  return res.status(200).send(delivery)
}

export const readDelivery = async (req: Request, res: Response, next: NextFunction) => {
  const delivery = await Delivery.findById(req.params.id).populate(['sourceLocation', 'destinationLocation']).exec()
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

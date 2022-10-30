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
    status: DeliveryStatus.PENDING
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

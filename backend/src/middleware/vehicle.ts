import { NextFunction, Request, Response } from 'express'
import { Vehicle } from '../model/Vehicle'

export const createVehicle = async (req: Request, res: Response, next: NextFunction) => {
  const vehicle = Vehicle.build({
    ...req.body
  }
  )

  await vehicle.save()
  console.log()
  res.locals.dbUser!!.vehicle = vehicle._id
  console.log(res.locals.dbUser)
  await res.locals.dbUser?.save()
  return res.status(201).send(vehicle)
}

export const updateVehicle = async (req: Request, res: Response, next: NextFunction) => {
  const vehicle = await Vehicle.findByIdAndUpdate(req.params.id.toString(),
    { ...req.body }).exec()
  if (vehicle == null) {
    return res.sendStatus(404)
  }
  return res.status(201).send(vehicle)
}

export const returnVehicle = async (req: Request, res: Response, next: NextFunction) => {
  return res.status(200).send(res.locals.vehicle)
}

export const readVehicle = async (req: Request, res: Response, next: NextFunction) => {
  const vehicle = await Vehicle.findById(req.params.id).exec()
  if (vehicle == null) {
    return res.sendStatus(404)
  }
  res.locals.vehicle = vehicle
  return next()
}

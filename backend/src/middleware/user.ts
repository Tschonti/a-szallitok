import { User, UserDoc } from '../model/User'
import { NextFunction, Request, Response } from 'express'
import { TransportRequest } from '../model/TransportRequest'
import { DeliveryDoc } from '../model/Delivery'

export const getUser = async (req: Request, res: Response) => {
  const user = await User.findById(req.params.id).exec()
  if (user == null) {
    return res.sendStatus(404)
  }
  return res.status(200).send(user)
}

export const updateUser = async (req: Request, res: Response) => {
  try {
    const user = await User.findByIdAndUpdate(res.locals.dbUser?._id.toString(),
      { phoneNumber: req.body.phoneNumber }).exec()
    if (user == null) {
      return res.sendStatus(404)
    }
    return res.status(201).send(user)
  } catch (e) {
    console.error(e)
    return res.sendStatus(404)
  }
}

export const checkIfAdmin = (req: Request, res: Response, next: NextFunction) => {
  if (!res.locals.dbUser) {
    return res.sendStatus(401)
  }
  if (!res.locals.dbUser.isAdmin) {
    return res.sendStatus(403)
  }
  return next()
}

export const deleteLoggedInUser = async (req: Request, res: Response) => {
  try {
    const user = await User.findByIdAndDelete(res.locals.dbUser?._id.toString()).exec()
    if (user == null) {
      return res.sendStatus(404)
    }
    return res.status(201).send(user)
  } catch (e) {
    console.error(e)
    return res.sendStatus(404)
  }
}

export const deleteParamUser = async (req: Request, res: Response) => {
  const user = await User.findByIdAndDelete(req.params.id).exec()
  if (user == null) {
    return res.sendStatus(404)
  }
  return res.status(201).send(user)
}

export const getRequestedJobs = async (req: Request, res: Response) => {
  return res.status(200).send(
    await TransportRequest.find({ user: res.locals.dbUser?._id }).populate('delivery').exec()
  )
}

export const getJobRequests = async (req: Request, res: Response) => {
  const allRequests = await TransportRequest
    .find({})
    .populate<{delivery: DeliveryDoc, user: UserDoc}>(['delivery', 'user'])
    .exec()
  return res.status(200).send(allRequests.filter(r => r.delivery.clientUser.toString() === res.locals.dbUser?._id))
}

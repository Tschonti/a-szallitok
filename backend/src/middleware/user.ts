import { User } from '../model/User'
import { NextFunction, Request, Response } from 'express'

export const getUser = async (req: Request, res: Response) => {
  try {
    const user = await User.findById(req.params.id).exec()
    if (user == null) {
      return res.sendStatus(404)
    }
    return res.status(200).send(user)
  } catch (e) {
    return res.sendStatus(404)
  }
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
  try {
    const user = await User.findByIdAndDelete(req.params.id).exec()
    if (user == null) {
      return res.sendStatus(404)
    }
    return res.status(201).send(user)
  } catch (e) {
    console.error(e)
    return res.sendStatus(404)
  }
}

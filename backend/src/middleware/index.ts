import { NextFunction, Request, Response } from 'express'
import { app } from '../config/firebase'
import { User } from '../model/User'

export const decodeToken = async (req: Request, res: Response, next: NextFunction) => {
  const authHeader = req.headers.authorization
  if (authHeader) {
    try {
      res.locals.user = await app.auth().verifyIdToken(authHeader.split(' ')[1] || '')
      return next()
    } catch (e) {
      console.error(e)
      return res.sendStatus(403)
    }
  } else {
    return res.sendStatus(401)
  }
}

export const registerUser = async (req: Request, res: Response, next: NextFunction) => {
  const newUser = User.build({
    firstName: 'Én',
    lastName: 'Vagyok',
    email: res.locals.user.email,
    googleToken: res.locals.user.uid,
    phoneNumber: res.locals.user.phone_number,
    isAdmin: false
  })
  await newUser.save()
  return res.status(201).send(newUser)
}

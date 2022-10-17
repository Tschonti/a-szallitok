import { NextFunction, Request, Response } from 'express'
import { app } from '../config/firebase'

export const decodeToken = async (req: Request, res: Response, next: NextFunction) => {
  const authHeader = req.headers.authorization
  if (authHeader) {
    try {
      res.locals.user = await app.auth().verifyIdToken(authHeader.split(' ')[1] || '')
      return next()
    } catch (e) {
      return res.sendStatus(403)
    }
  } else {
    return res.sendStatus(401)
  }
}

import { NextFunction, Request, Response } from 'express'
import { app } from '../config/firebase'
import { User } from '../model/User'

// Reads the JWT token from the Auth header, sends it to Firebase for verification.
// If verified, saves the data extracted from the token in res.locals
export const decodeToken = async (req: Request, res: Response, next: NextFunction) => {
  const authHeader = req.headers.authorization
  if (authHeader) {
    try {
      const user = await app.auth().verifyIdToken(authHeader.split(' ')[1] || '')
      res.locals.googleUser = user
      res.locals.name = user.name
      return next()
    } catch (e) {
      console.error(e)
      return res.sendStatus(403)
    }
  } else {
    return res.sendStatus(401)
  }
}

// Find the user in the databse with the Google UID from res.locals, and stores it in res.locals.dbUser
// If the user doesn't exist in db, it stores null
export const getUserByUId = async (req: Request, res: Response, next: NextFunction) => {
  if (!res.locals.googleUser?.uid) {
    return res.sendStatus(401)
  }
  res.locals.dbUser = await User.findOne({ googleToken: res.locals.googleUser.uid }).exec()
  return next()
}

// If res.locals.dbUser is not null, so the user who sent the request isn't in the db,
// it creates a User document for them.
export const registerUser = async (req: Request, res: Response, next: NextFunction) => {
  if (res.locals.dbUser == null) {
    if (!res.locals.googleUser?.uid) {
      return res.sendStatus(401)
    }
    const newUser = User.build({
      name: res.locals.name || '',
      email: res.locals.googleUser.email || '',
      googleToken: res.locals.googleUser.uid,
      phoneNumber: res.locals.googleUser.phone_number,
      profilePictureUrl: res.locals.googleUser.picture,
      isAdmin: false
    })
    await newUser.save()
    return res.status(201).send(newUser)
  } else {
    console.log(`User with name ${res.locals.dbUser.name} already registered.`)
    return res.status(200).send(res.locals.dbUser)
  }
}

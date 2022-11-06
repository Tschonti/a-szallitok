import { NextFunction, Request, Response } from 'express'
import { validationResult } from 'express-validator'

export const checkValidationResult = async (req: Request, res: Response, next: NextFunction) => {
  const errors = validationResult(req)
  if (errors.isEmpty()) {
    return next()
  }
  console.error(errors)
  return res.status(400).send(errors)
}

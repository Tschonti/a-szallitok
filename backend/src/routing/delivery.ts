import { Express, Request, Response } from 'express'
import { body, param } from 'express-validator'
import { getUserByUId } from '../middleware/auth'
import {
  addRequestMiddleware,
  createDelivery,
  deleteDelivery,
  rateClientMiddleware,
  rateTransporterMiddleware,
  readDelivery,
  replyMiddleware,
  returnDelivery,
  statusChangeMiddleware,
  updateDelivery
} from '../middleware/delivery'
import { checkValidationResult } from '../middleware/validation'
import { Delivery, DeliveryStatus } from '../model/Delivery'

export default (app: Express) => {
  app.get('/delivery', async (req: Request, res: Response) => {
    return res.status(200).send(await Delivery.find({
      status: { $in: [DeliveryStatus.UNASSIGNED, DeliveryStatus.PENDING] }
    }))
  })

  app.post('/delivery',
    body('title').isString().notEmpty(),
    body('description').isString().notEmpty(),
    body('pickUpFrom').isDate({ format: 'YYYY.MM.DD' }),
    body('pickUpUntil').isDate({ format: 'YYYY.MM.DD' }),
    body('price').isDecimal(),
    body('capacity.weight').isDecimal(),
    body('capacity.length').isDecimal(),
    body('capacity.height').isDecimal(),
    body('capacity.width').isDecimal(),
    body('sourceLocation.country').isString().notEmpty(),
    body('sourceLocation.address').isString().notEmpty(),
    body('sourceLocation.city').isString().notEmpty(),
    body('sourceLocation.postalCode').isInt(),
    body('sourceLocation.coordinate.longitude').isDecimal(),
    body('sourceLocation.coordinate.latitude').isDecimal(),
    body('destinationLocation.country').isString().notEmpty(),
    body('destinationLocation.address').isString().notEmpty(),
    body('destinationLocation.city').isString().notEmpty(),
    body('destinationLocation.postalCode').isInt(),
    body('destinationLocation.coordinate.longitude').isDecimal(),
    body('destinationLocation.coordinate.latitude').isDecimal(),
    body('pictureUrl').optional().isURL(),
    checkValidationResult,
    getUserByUId, createDelivery)

  app.get('/delivery/:id', param('id').isMongoId(), checkValidationResult, readDelivery, returnDelivery)

  app.put('/delivery/:id',
    param('id').isMongoId(),
    body('title').isString().notEmpty(),
    body('description').isString().notEmpty(),
    body('pickUpFrom').isDate({ format: 'YYYY.MM.DD' }),
    body('pickUpUntil').isDate({ format: 'YYYY.MM.DD' }),
    body('price').isDecimal(),
    body('capacity.weight').isDecimal(),
    body('capacity.length').isDecimal(),
    body('capacity.height').isDecimal(),
    body('capacity.width').isDecimal(),
    body('sourceLocation.country').isString().notEmpty(),
    body('sourceLocation.address').isString().notEmpty(),
    body('sourceLocation.city').isString().notEmpty(),
    body('sourceLocation.postalCode').isInt(),
    body('sourceLocation.coordinate.longitude').isDecimal(),
    body('sourceLocation.coordinate.latitude').isDecimal(),
    body('destinationLocation.country').isString().notEmpty(),
    body('destinationLocation.address').isString().notEmpty(),
    body('destinationLocation.city').isString().notEmpty(),
    body('destinationLocation.postalCode').isInt(),
    body('destinationLocation.coordinate.longitude').isDecimal(),
    body('destinationLocation.coordinate.latitude').isDecimal(),
    body('pictureUrl').optional().isURL(),
    checkValidationResult,
    getUserByUId, readDelivery, updateDelivery
  )

  app.delete('/delivery/:id', param('id').isMongoId(), getUserByUId, readDelivery, deleteDelivery)

  app.put('/delivery/:id/rateTransporter', param('id').isMongoId(), body('rating').isInt({ min: 1, max: 5 }),
    checkValidationResult, getUserByUId, readDelivery, rateTransporterMiddleware)

  app.put('/delivery/:id/rateClient', param('id').isMongoId(), body('rating').isInt({ min: 1, max: 5 }),
    checkValidationResult, getUserByUId, readDelivery, rateClientMiddleware)

  app.put('/delivery/:id/statusChange', param('id').isMongoId(), body('status').custom((value) => {
    if ([DeliveryStatus.IN_TRANSIT, DeliveryStatus.DELIVERED].includes(value)) {
      return true
    }
    throw new Error('Invalid status')
  }), checkValidationResult, getUserByUId, readDelivery, statusChangeMiddleware)

  app.post('/delivery/:id/request', param('id').isMongoId(),
    checkValidationResult, getUserByUId, readDelivery, addRequestMiddleware)

  app.put('/delivery/:id/reply', param('id').isMongoId(), body('userId').isMongoId(), body('accept').isBoolean(),
    checkValidationResult, getUserByUId, readDelivery, replyMiddleware)
}

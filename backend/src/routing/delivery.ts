import { Express, Request, Response } from 'express'
import { body, param } from 'express-validator'
import { getUserByUId } from '../middleware/auth'
import {
  createDelivery,
  readDelivery,
  returnDelivery,
  statusChangeMiddleware
} from '../middleware/delivery'
import { checkValidationResult } from '../middleware/validation'
import { mockDelivery } from '../mockdata'
import { Delivery, DeliveryStatus } from '../model/Delivery'

export default (app: Express) => {
  app.get('/delivery', async (req: Request, res: Response) => {
    return res.status(200).send(await Delivery.find())
  })

  app.post('/delivery',
    body('title').isString().notEmpty(),
    body('description').isString().notEmpty(),
    body('pickUpFrom').isISO8601().toDate(),
    body('pickUpUntil').isISO8601().toDate(),
    body('price').isDecimal(),
    /* body('weight').isDecimal(),
    body('length').isDecimal(),
    body('height').isDecimal(),
    body('width').isDecimal(), */
    body('pictureUrl').optional().isURL(),
    checkValidationResult,
    getUserByUId, createDelivery)

  app.get('/delivery/:id', param('id').isMongoId(), checkValidationResult, readDelivery, returnDelivery)

  app.put('/delivery/:id', (req: Request, res: Response) => {
    res.send(mockDelivery)
  })

  app.delete('/delivery/:id', (req: Request, res: Response) => {
    res.send(mockDelivery)
  })

  app.put('/delivery/:id/rateTransporter', (req: Request, res: Response) => {
    res.send(mockDelivery)
  })

  app.put('/delivery/:id/rateClient', (req: Request, res: Response) => {
    res.send(mockDelivery)
  })

  app.put('/delivery/:id/statusChange', param('id').isMongoId(), body('status').custom((value) => {
    if ([DeliveryStatus.IN_TRANSIT, DeliveryStatus.DELIVERED].includes(value)) {
      return true
    }
    throw new Error('Invalid status')
  }), checkValidationResult, getUserByUId, readDelivery, statusChangeMiddleware)

  app.post('/delivery/:id/request', (req: Request, res: Response) => {
    res.send(mockDelivery)
  })

  app.put('/delivery/:id/reply', (req: Request, res: Response) => {
    res.send(mockDelivery)
  })
}

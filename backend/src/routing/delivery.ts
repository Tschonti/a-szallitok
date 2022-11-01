import { Express, Request, Response } from 'express'
import { body } from 'express-validator'
import { getUserByUId } from '../middleware/auth'
import { createDelivery, createLocationEntities } from '../middleware/delivery'
import { checkValidationResult } from '../middleware/validation'
import { mockDelivery, mockJobDetails } from '../mockdata'

export default (app: Express) => {
  app.get('/delivery', (req: Request, res: Response) => {
    res.send([mockDelivery, mockDelivery])
  })

  app.post('/delivery',
    body('title').isString().notEmpty(),
    body('description').isString().notEmpty(),
    body('pickUpFrom').isISO8601().toDate(),
    body('pickUpUntil').isISO8601().toDate(),
    body('price').isDecimal(),
    body('weight').isDecimal(),
    body('length').isDecimal(),
    body('height').isDecimal(),
    body('width').isDecimal(),
    body('pictureUrl').optional().isURL(),
    checkValidationResult,
    getUserByUId, createLocationEntities, createDelivery)

  app.get('/delivery/jobDetails', (req: Request, res: Response) => {
    res.send([mockJobDetails, mockJobDetails])
  })

  app.get('/delivery/jobDetails/:id', (req: Request, res: Response) => {
    res.send(mockJobDetails)
  })

  app.get('/delivery/:id', (req: Request, res: Response) => {
    res.send(mockDelivery)
  })

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

  app.put('/delivery/:id/statusChange', (req: Request, res: Response) => {
    res.send(mockDelivery)
  })

  app.post('/delivery/:id/request', (req: Request, res: Response) => {
    res.send(mockDelivery)
  })

  app.put('/delivery/:id/reply', (req: Request, res: Response) => {
    res.send(mockDelivery)
  })
}

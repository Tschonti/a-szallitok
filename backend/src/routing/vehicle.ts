import { Express } from 'express'
import { body, param } from 'express-validator'
import { createVehicle, readVehicle, returnVehicle, updateVehicle } from '../middleware/vehicle'
import { checkValidationResult } from '../middleware/validation'
import { getUserByUId } from '../middleware/auth'

export default (app: Express) => {
  app.get('/vehicle/:id', param('id').isMongoId(), checkValidationResult, readVehicle, returnVehicle)

  app.put('/vehicle/:id', param('id').isMongoId(),
    body('yearOfManufacturing').isDecimal(),
    body('location').optional().isString().notEmpty(),
    body('plateNumber').isString().notEmpty(),
    body('maxCapacity.weight').isDecimal(),
    body('maxCapacity.length').isDecimal(),
    body('maxCapacity.height').isDecimal(),
    body('maxCapacity.width').isDecimal(),
    body('type').isString().notEmpty(),
    body('pictureUrl').optional().isURL(),
    checkValidationResult,
    getUserByUId, updateVehicle)

  app.post('/vehicle',
    body('yearOfManufacturing').isDecimal(),
    body('location').optional().isString().notEmpty(),
    body('plateNumber').isString().notEmpty(),
    body('maxCapacity.weight').isDecimal(),
    body('maxCapacity.length').isDecimal(),
    body('maxCapacity.height').isDecimal(),
    body('maxCapacity.width').isDecimal(),
    body('type').isString().notEmpty(),
    body('pictureUrl').optional().isURL(),
    checkValidationResult,
    getUserByUId, createVehicle)
}

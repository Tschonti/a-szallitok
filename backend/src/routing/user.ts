import { Express, Request, Response } from 'express'
import { param } from 'express-validator'
import { getUserByUId } from '../middleware/auth'
import {
  calculateRating,
  checkIfAdmin,
  deleteLoggedInUser,
  deleteParamUser,
  getJobRequests,
  getRequestedJobs,
  getUser,
  promote,
  updateUser
} from '../middleware/user'
import { checkValidationResult } from '../middleware/validation'
import { mockUserInToplist } from '../mockdata'

export default (app: Express) => {
  app.get('/user/requestedJobs', getUserByUId, getRequestedJobs)
  app.get('/user/jobRequests', getUserByUId, getJobRequests)

  app.get('/user/:id', param('id').isMongoId(), checkValidationResult, getUser, calculateRating)

  app.put('/user/', getUserByUId, updateUser)

  app.delete('/user/', getUserByUId, deleteLoggedInUser)

  app.delete('/user/:id', param('id').isMongoId(), checkValidationResult, getUserByUId, checkIfAdmin, deleteParamUser)

  app.put('/user/:id/promote', param('id').isMongoId(), getUserByUId, checkIfAdmin, promote)

  app.get('/user/toplist', (req: Request, res: Response) => {
    res.send([mockUserInToplist, mockUserInToplist, mockUserInToplist])
  })
}

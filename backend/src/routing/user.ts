import { Express, Request, Response } from 'express'
import { param } from 'express-validator'
import { getUserByUId } from '../middleware/auth'
import {
  checkIfAdmin,
  deleteLoggedInUser,
  deleteParamUser,
  getJobRequests,
  getRequestedJobs,
  getUser,
  updateUser
} from '../middleware/user'
import { checkValidationResult } from '../middleware/validation'
import { mockDelivery, mockUser, mockUserInToplist } from '../mockdata'

export default (app: Express) => {
  app.get('/user/requestedJobs', getUserByUId, getRequestedJobs)
  app.get('/user/jobRequests', getUserByUId, getJobRequests)

  app.get('/user/:id', param('id').isMongoId(), checkValidationResult, getUser)

  app.put('/user/', getUserByUId, updateUser)

  app.delete('/user/', getUserByUId, deleteLoggedInUser)

  app.delete('/user/:id', param('id').isMongoId(), checkValidationResult, getUserByUId, checkIfAdmin, deleteParamUser)

  app.get('/user/:id/history', (req: Request, res: Response) => {
    res.send([mockDelivery, mockDelivery])
  })

  app.put('/user/:id/promote', (req: Request, res: Response) => {
    res.send(mockUser)
  })

  app.get('/user/toplist', (req: Request, res: Response) => {
    res.send([mockUserInToplist, mockUserInToplist, mockUserInToplist])
  })
}

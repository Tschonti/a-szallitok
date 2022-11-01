import { Express, Request, Response } from 'express'
import { getUserByUId } from '../middleware/auth'
import { checkIfAdmin, deleteLoggedInUser, deleteParamUser, getUser, updateUser } from '../middleware/user'
import { mockDelivery, mockUser, mockUserInToplist } from '../mockdata'

export default (app: Express) => {
  app.get('/user', (req: Request, res: Response) => {
    res.send([mockUser, mockUser])
  })

  app.post('/user', (req: Request, res: Response) => {
    res.send(mockUser)
  })

  app.get('/user/:id', getUser)

  app.put('/user/', getUserByUId, updateUser)

  app.delete('/user/', getUserByUId, deleteLoggedInUser)

  app.delete('/user/:id', getUserByUId, checkIfAdmin, deleteParamUser)

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

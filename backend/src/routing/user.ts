import { Express, Request, Response } from 'express'
import { mockUser } from '../mockdata'

export default (app: Express) => {
  app.get('/user', (req: Request, res: Response) => {
    res.send([mockUser, mockUser])
  })

  app.get('/user/:id', (req: Request, res: Response) => {
    res.send(mockUser)
  })
}
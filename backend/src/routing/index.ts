import { Express, Request, Response } from 'express'
import { decodeToken, getUserByUId, registerUser } from '../middleware/auth'
import delivery from './delivery'
import user from './user'
import vehicle from './vehicle'

export default (app: Express) => {
  app.get('/', (req: Request, res: Response) => {
    res.send('A szállítók')
  })
  app.get('/login', decodeToken, getUserByUId, registerUser)
  delivery(app)
  user(app)
  vehicle(app)
}

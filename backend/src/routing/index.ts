import { Express, Request, Response } from 'express'
import { decodeToken, registerUser } from '../middleware'
import delivery from './delivery'
import user from './user'
import vehicle from './vehicle'

export default (app: Express) => {
  app.get('/', (req: Request, res: Response) => {
    res.send('A szállítók')
  })
  app.get('/guarded', decodeToken, registerUser)
  delivery(app)
  user(app)
  vehicle(app)
}

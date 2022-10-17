import { Express, Request, Response } from 'express'
import { decodeToken } from '../middleware'
import delivery from './delivery'
import user from './user'
import vehicle from './vehicle'

export default (app: Express) => {
  app.get('/', (req: Request, res: Response) => {
    res.send('A szállítók')
  })
  app.get('/guarded', decodeToken, (req: Request, res: Response) => {
    console.log(res.locals.user.email)
    res.send(res.locals.user.email)
  })
  delivery(app)
  user(app)
  vehicle(app)
}

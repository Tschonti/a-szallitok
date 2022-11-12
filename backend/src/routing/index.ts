import { Express, Request, Response } from 'express'
import { decodeToken, getUserByUId, registerUser } from '../middleware/auth'
import delivery from './delivery'
import user from './user'
import vehicle from './vehicle'
import * as openapiDesc from '../config/openapi.json'
import { serve, setup } from 'swagger-ui-express'
import { calculateRating } from '../middleware/user'

export default (app: Express) => {
  app.get('/', (req: Request, res: Response) => {
    res.send('A szállítók')
  })
  app.use('/api-docs', serve, setup(openapiDesc))
  app.use(decodeToken)
  app.get('/login', getUserByUId, registerUser, calculateRating)
  delivery(app)
  user(app)
  vehicle(app)
}

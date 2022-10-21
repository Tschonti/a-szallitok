import { Express, Request, Response } from 'express'
import { mockVehicle } from '../mockdata'

export default (app: Express) => {
  app.get('/vehicle/:id', (req: Request, res: Response) => {
    res.send(mockVehicle)
  })

  app.put('/vehicle/:id', (req: Request, res: Response) => {
    res.send(mockVehicle)
  })

  app.post('/vehicle', (req: Request, res: Response) => {
    res.send(mockVehicle)
  })
}

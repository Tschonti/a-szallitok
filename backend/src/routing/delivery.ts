import { Express, Request, Response } from 'express'

export default (app: Express) => {
  app.get('/delivery', (req: Request, res: Response) => {
    res.send({name: "Első fuvar"})
  })

  app.get('/delivery/:id', (req: Request, res: Response) => {
    res.send({name: req.params.id})
  })
}
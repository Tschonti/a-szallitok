import { Express, Request, Response } from 'express'
import { mockJobDetails, mockUser, mockUserInToplist, mockUserWithRating } from '../mockdata'

export default (app: Express) => {
  app.get('/user', (req: Request, res: Response) => {
    res.send([mockUser, mockUser])
  })

  app.post('/user', (req: Request, res: Response) => {
    res.send(mockUser)
  })

  app.get('/user/:id', (req: Request, res: Response) => {
    res.send(mockUser)
  })

  app.put('/user/:id', (req: Request, res: Response) => {
    res.send(mockUser)
  })

  app.delete('/user/:id', (req: Request, res: Response) => {
    res.send(mockUser)
  })

  app.get('/user/:id/rating', (req: Request, res: Response) => {
    res.send(mockUserWithRating)
  })

  app.get('/user/:id/history', (req: Request, res: Response) => {
    res.send([mockJobDetails, mockJobDetails])
  })

  app.put('/user/:id/promote', (req: Request, res: Response) => {
    res.send(mockUser)
  })

  app.get('/user/toplist', (req: Request, res: Response) => {
    res.send([mockUserInToplist, mockUserInToplist, mockUserInToplist])
  })
}

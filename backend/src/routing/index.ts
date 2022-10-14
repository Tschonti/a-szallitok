import { Express, Request, Response } from 'express';
import delivery from './delivery';
import user from './user';

export default (app: Express) => {

  app.get('/', (req: Request, res: Response) => {
    res.send('A szállítók');
  });
  delivery(app)
  user(app)
}
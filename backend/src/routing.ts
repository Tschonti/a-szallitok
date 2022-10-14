import { Express, Request, Response } from 'express';

export default (app: Express) => {

  app.get('/', (req: Request, res: Response) => {
    res.send('A szállítók');
  });

  app.get('/deliveries', (req: Request, res: Response) => {
    res.send({name: "Első fuvar"});
  });
}
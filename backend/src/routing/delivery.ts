import { Express, Request, Response } from 'express';
import { mockDelivery, mockJobDetails } from '../mockdata';

export default (app: Express) => {
  app.get('/delivery', (req: Request, res: Response) => {
    res.send([mockDelivery, mockDelivery, ]);
  });

  app.post('/delivery', (req: Request, res: Response) => {
    res.send(mockDelivery);
  });

  app.get('/delivery/:id', (req: Request, res: Response) => {
    res.send(mockDelivery);
  });

  app.put('/delivery/:id', (req: Request, res: Response) => {
    res.send(mockDelivery);
  });

  app.delete('/delivery/:id', (req: Request, res: Response) => {
    res.send(mockDelivery);
  });

  app.put('/delivery/:id/rateTransporter', (req: Request, res: Response) => {
    res.send(mockDelivery);
  });

  app.put('/delivery/:id/rateClient', (req: Request, res: Response) => {
    res.send(mockDelivery);
  });

  app.get('/delivery/:id/jobDetails', (req: Request, res: Response) => {
    res.send(mockJobDetails);
  });
};

import { NextFunction, Request, Response } from "express";
import { app } from "../config/firebase";

export const decodeToken = async (req: Request, res: Response, next: NextFunction) => {
    const token = req.headers.authorization?.split(' ')[1]
    try {
        const decodeValue = await app.auth().verifyIdToken(token || '')
        if (decodeValue) {
            return next()
        }
        return res.status(401).send('Unauthorized!')
    } catch(e) {
        return res.status(500)
    }

}
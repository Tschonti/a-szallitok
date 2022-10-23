import express, { Express } from 'express'
import dotenv from 'dotenv'
import routing from './routing'
import cors from 'cors'
import mongoose from 'mongoose'

dotenv.config()

const app: Express = express()
const port = process.env.PORT
app.use(express.json())
app.use(cors())

routing(app)
// eslint-disable-next-line max-len
const connStr = `mongodb+srv://${process.env.DB_USER || ''}:${process.env.DB_PWD || ''}@cluster0.4evepic.mongodb.net/?retryWrites=true&w=majority`
mongoose.connect(connStr).then(() => console.log('Connected to db!')).catch((e: Error) => console.error(e))

app.listen(port, () => {
  console.log(`⚡️[server]: Server is running at http://localhost:${port || 8000}`)
})

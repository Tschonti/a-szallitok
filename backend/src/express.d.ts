import 'express'
import { DecodedIdToken } from 'firebase-admin/lib/auth/token-verifier'
import { User } from '../model/User'

interface Locals {
  googleUser?: DecodedIdToken
  dbUser?: User
  name?: string
}

declare module 'express' {
  export interface Response {
    locals: Locals
  }
}

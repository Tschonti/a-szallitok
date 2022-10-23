import 'express'
import { DecodedIdToken } from 'firebase-admin/lib/auth/token-verifier'
import { UserDoc } from './model/User'

interface Locals {
  googleUser?: DecodedIdToken
  dbUser?: UserDoc | null
  name?: string
}

declare module 'express' {
  export interface Response {
    locals: Locals
  }
}

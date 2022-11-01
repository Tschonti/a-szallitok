import 'express'
import { DecodedIdToken } from 'firebase-admin/lib/auth/token-verifier'
import { DeliveryDoc } from './model/Delivery'
import { LocationDoc } from './model/Location'
import { UserDoc } from './model/User'

interface Locals {
  googleUser?: DecodedIdToken
  dbUser?: UserDoc | null
  name?: string
  sourceLocation?: LocationDoc
  destinationLocation?: LocationDoc
  delivery?: DeliveryDoc
}

declare module 'express' {
  export interface Response {
    locals: Locals
  }
}

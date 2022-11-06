import 'express'
import { DecodedIdToken } from 'firebase-admin/lib/auth/token-verifier'
import { DeliveryDoc } from './model/Delivery'
import { LocationDoc } from './model/Location'
import { UserDoc } from './model/User'
import { VehicleDoc } from './model/Vehicle'

interface Locals {
  googleUser?: DecodedIdToken
  dbUser?: UserDoc | null
  name?: string
  sourceLocation?: LocationDoc
  destinationLocation?: LocationDoc
  delivery?: DeliveryDoc
  vehicle?: VehicleDoc
}

declare module 'express' {
  export interface Response {
    locals: Locals
  }
}

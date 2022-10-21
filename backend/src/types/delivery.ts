import { Location } from './location'

export interface Delivery {
  title: string
  description: string
  sourceLocation: Location
  destinationLocation: Location
  clientUserId: number
  transporterUserId?: number
  pickUpFrom: Date
  pickUpUntil: Date
  price: number
  clientRating?: number
  transporterRating?: number
  capacity: {
    weight: number
    height: number
    length: number
    width: number
  }
  status: string
  pictureUrl?: string
}

import { ILocation } from './location'

export interface IDelivery {
  title: string
  description: string
  sourceLocation: ILocation
  destinationLocation: ILocation
  clientUser: string
  transporterUser?: string
  pickUpFrom: String
  pickUpUntil: String
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

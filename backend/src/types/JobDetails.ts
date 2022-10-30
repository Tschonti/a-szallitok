import { ILocation } from './location'

export interface IJobDetails {
  title: string
  description: string
  clientId: string
  transporterId?: string
  deliveryId: string
  clientName: string
  clientAvgRating: number
  imPaths: string
  deliveryDate: Date
  deliveryCost: number
  deliveryLocation: ILocation
}

import { ILocation } from './location'

export interface IJobDetails {
  name: string
  imPaths: string
  avgRating: number
  deliveryDate: Date
  deliveryCost: number
  deliveryLocation: ILocation
}

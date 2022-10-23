import { ILocation } from './location'

export interface IJobDetails {
  _id: string
  title: string
  description: string
  clientName: string
  transporterName?: string
  imPaths: string
  avgRating: number
  deliveryDate: Date
  deliveryCost: number
  deliveryLocation: ILocation
}

import { Location } from './location'

export interface JobDetails {
  _id: string
  title: string
  description: string
  clientName: string
  transporterName?: string
  imPaths: string
  avgRating: number
  deliveryDate: Date
  deliveryCost: number
  deliveryLocation: Location
}

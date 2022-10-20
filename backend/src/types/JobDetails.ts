import { Location } from './location'

export interface JobDetails {
  name: string
  imPaths: string
  avgRating: number
  deliveryDate: Date
  deliveryCost: number
  deliveryLocation: Location
}

import { ICoordinate } from './coordinate'

export interface ILocation {
  coordinate: ICoordinate
  country: string
  postalCode: Number
  city: string
  address: string
}

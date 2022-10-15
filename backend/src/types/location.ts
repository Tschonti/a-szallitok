import { Coordinate } from './coordinate';

export interface Location {
  coordinate: Coordinate
  country: string
  postalCode: number
  city: string
  address: string
}

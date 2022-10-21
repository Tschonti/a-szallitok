export interface IVehicle {
  plateNumber: string
  type: string
  yearOfManufacturing: number
  location?: string
  maxCapacity: {
    weight: number
    height: number
    length: number
    width: number
  }
  pictureUrl?: string
}

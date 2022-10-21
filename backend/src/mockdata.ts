import { Delivery } from './types/delivery'
import { JobDetails } from './types/JobDetails'
import { IUser } from './types/user'
import { IVehicle } from './types/vehicle'
import { Location } from './types/location'
import { Coordinate } from './types/coordinate'
import { UserInToplist } from './types/userInToplist'

export const mockUser: IUser = {
  profilePictureUrl: 'files:///defaultImage.png',
  firstName: 'John',
  lastName: 'James',
  googleToken: 'U2314251234234',
  phoneNumber: '12345',
  vehicleId: 4,
  isAdmin: true,
  email: 'john@email.com'
}

export const mockJobDetails: JobDetails = {
  name: 'John Doe',
  imPaths: 'files:///defaultImage.png',
  avgRating: 4.5,
  deliveryDate: new Date('2019-01-16'),
  deliveryCost: 10000,
  deliveryLocation: {
    country: 'Hungary',
    coordinate: {
      latitude: -19.47381,
      longitude: 14.45529
    },
    address: 'Irinyi József utca 42',
    city: 'Budapest',
    postalCode: 1117
  }
}

export const mockUserInToplist: UserInToplist = {
  firstName: 'John',
  lastName: 'James',
  moneyEarned: 100000,
  deliveriesCompleted: 10,
  id: 10
}

export const mockCoordinate: Coordinate = {
  latitude: -19.47381,
  longitude: 14.45529
}

export const mockLocation: Location = {
  country: 'Hungary',
  coordinate: mockCoordinate,
  address: 'Irinyi József utca 42',
  city: 'Budapest',
  postalCode: 1117
}

export const mockDelivery: Delivery = {
  clientUserId: 2,
  transporterUserId: 2,
  pictureUrl: 'file:///defaultImage.png',
  length: 2,
  description: 'Please deliver it!',
  destinationLocation: mockLocation,
  weight: 2,
  title: 'Brick delivery',
  createdAt: new Date(),
  clientRating: 2,
  pickUpUntil: new Date(),
  price: 10000,
  width: 2,
  sourceLocation: {
    country: 'Hungary',
    coordinate: {
      latitude: -19.47381,
      longitude: 14.45529
    },
    address: 'Irinyi József utca 42',
    city: 'Budapest',
    postalCode: 1117
  },
  transporterRating: 2,
  id: 2,
  pickUpFrom: new Date(),
  height: 2,
  status: 'DONE',
  updatedAt: new Date()
}

export const mockVehicle: IVehicle = {
  maxHeight: 2,
  pictureUrl: 'file:///defaultImage.png',
  yearOfManufacturing: 2008,
  location: 'Telephely',
  maxWeight: 2,
  plateNumber: 'AD0000',
  type: 'Volkswagen Transporter',
  maxLength: 2,
  maxWidth: 2
}

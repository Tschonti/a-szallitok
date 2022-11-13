import { IDelivery } from './types/delivery'
import { UserDto } from './types/user'
import { IVehicle } from './types/vehicle'
import { ILocation } from './types/location'
import { ICoordinate } from './types/coordinate'
import { IUserInToplist } from './types/userInToplist'
import { DeliveryWithUserStatus } from './types/deliveryWithUserStatus'
import { TransportRequestStatus } from './model/TransportRequest'

export const mockUser: UserDto = {
  _id: 'mongoDbRandomId',
  profilePictureUrl: 'https://cdn-icons-png.flaticon.com/512/685/685681.png',
  name: 'John James',
  googleToken: 'U2314251234234',
  phoneNumber: '12345',
  vehicle: 'mongoID',
  isAdmin: true,
  email: 'john@email.com',
  avgRating: 3.45
}

export const mockUserInToplist: IUserInToplist = {
  name: 'John',
  moneyEarned: 100000,
  deliveriesCompleted: 10,
  id: 10
}

export const mockCoordinate: ICoordinate = {
  latitude: -19.47381,
  longitude: 14.45529
}

export const mockLocation: ILocation = {
  country: 'Hungary',
  coordinate: mockCoordinate,
  address: 'Irinyi József utca 42',
  city: 'Budapest',
  postalCode: 1117
}

export const mockDelivery: IDelivery = {
  clientUser: 'mongodbRandomId',
  transporterUser: 'mongodbRandomId',
  pictureUrl: 'https://cdn-icons-png.flaticon.com/512/685/685681.png',
  description: 'Please deliver it!',
  destinationLocation: mockLocation,
  title: 'Brick delivery',
  clientRating: 2,
  pickUpUntil: '2022.05.12',
  price: 10000,
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
  pickUpFrom: '2022.05.12',
  capacity: {
    height: 2,
    length: 2,
    weight: 2,
    width: 2
  },
  status: 'DONE'
}

export const mockVehicle: IVehicle = {
  pictureUrl: 'https://cdn-icons-png.flaticon.com/512/685/685681.png',
  yearOfManufacturing: 2008,
  location: 'Telephely',
  plateNumber: 'AD0000',
  type: 'Volkswagen Transporter',
  maxCapacity: {
    length: 2,
    width: 2,
    weight: 2,
    height: 2
  }
}

export const mockDeliveryWithUserStatus: DeliveryWithUserStatus = {
  delivery: mockDelivery,
  userStatus: TransportRequestStatus.REJECTED
}

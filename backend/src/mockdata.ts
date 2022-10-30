import { IDelivery } from './types/delivery'
import { IJobDetails } from './types/JobDetails'
import { IUser } from './types/user'
import { IVehicle } from './types/vehicle'
import { ILocation } from './types/location'
import { ICoordinate } from './types/coordinate'
import { IUserInToplist } from './types/userInToplist'
import { UserWithRating } from './types/userWithRating'

export const mockUser: IUser = {
  id: "mongoDbRandomId",
  profilePictureUrl: 'https://cdn-icons-png.flaticon.com/512/685/685681.png',
  name: 'John James',
  googleToken: 'U2314251234234',
  phoneNumber: '12345',
  vehicleId: 4,
  isAdmin: true,
  email: 'john@email.com',
  avgRating: 3.45
}

export const mockUserWithRating: UserWithRating = {
  _id: 'mongoDbRandomId',
  name: 'John Doe',
  avgRating: 3.45
}

export const mockJobDetails: IJobDetails = {
  title: 'Brick delivery',
  description: 'Please deliver it!',
  clientId: "randomMongoDbId",
  transporterId: "randomMongoDbId",
  deliveryId: 'randomMongoDbId',
  clientName: 'John Doe',
  clientAvgRating: 4.5,
  imPaths: 'https://cdn-icons-png.flaticon.com/512/685/685681.png',
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

export const mockUserInToplist: IUserInToplist = {
  firstName: 'John',
  lastName: 'James',
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
  pickUpUntil: new Date(),
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
  pickUpFrom: new Date(),
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

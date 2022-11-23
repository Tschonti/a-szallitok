import { User } from "./User"

export interface Delivery {
    _id: string
    title: string
    description: string
    sourceLocation: Location
    destinationLocation: Location
    clientUser: User
    transporterUser?: User
    pickUpFrom: String
    pickUpUntil: String
    price: number
    clientRating?: number
    transporterRating?: number
    capacity: {
        weight: number
        height: number
        length: number
        width: number
    }
    status: string
    pictureUrl?: string
    transporterLocation?: Coordinate
}

export interface Location {
    coordinate: Coordinate
    country: string
    postalCode: Number
    city: string
    address: string
}

export interface Coordinate {
    longitude: number
    latitude: number
}
export interface IUser {
  id: string,
  name: string
  email: string
  googleToken: string
  phoneNumber?: string
  vehicleId?: number
  isAdmin: boolean
  profilePictureUrl?: string
  avgRating: number
}

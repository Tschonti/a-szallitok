export interface IUser {
  id: string,
  name: string
  email: string
  googleToken: string
  phoneNumber?: string
  vehicleId?: string
  isAdmin: boolean
  profilePictureUrl?: string
  avgRating: number
}

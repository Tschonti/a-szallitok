export interface IUser {
  name: string
  email: string
  googleToken: string
  phoneNumber?: string
  vehicleId?: number
  isAdmin: boolean
  profilePictureUrl?: string
}

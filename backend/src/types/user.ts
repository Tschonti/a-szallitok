export interface IUser {
  firstName: string
  lastName: string
  email: string
  googleToken: string
  phoneNumber?: string
  vehicleId?: number
  isAdmin: boolean
  profilePictureUrl?: string
}

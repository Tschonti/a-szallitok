export interface IUser {
  name: string
  email: string
  googleToken: string
  phoneNumber?: string
  vehicleId?: string
  isAdmin: boolean
  profilePictureUrl?: string
}

export interface UserDto extends IUser {
  id: string
  avgRating: number
}

export interface IUser {
  name: string
  email: string
  googleToken: string
  phoneNumber?: string
  vehicle?: string
  isAdmin: boolean
  profilePictureUrl?: string
}

export interface UserDto extends IUser {
  _id: string
  avgRating: number
}

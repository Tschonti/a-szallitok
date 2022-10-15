export interface User {
  id: number
  firstName: string
  lastName: string
  email: string
  googletoken: string
  phoneNumber: string
  vehicleId?: number
  isAdmin: boolean
  profilePictureUrl?: string
}

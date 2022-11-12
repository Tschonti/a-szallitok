export interface User {
    _id: string
    avgRating: number
    name: string
    email: string
    googleToken: string
    phoneNumber?: string
    vehicle?: string
    isAdmin: boolean
    profilePictureUrl?: string
  }

  export interface UserInToplist {
    id: number
    firstName: string
    lastName: string
    deliveriesCompleted: number
    moneyEarned: number
  }

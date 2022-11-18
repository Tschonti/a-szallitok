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
    _id: User
    deliveriesCompleted: number
    moneyEarned: number
  }

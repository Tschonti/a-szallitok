import { TransportRequestStatus } from '../model/TransportRequest'
import { IDelivery } from './delivery'
import { IUser } from './user'

export interface DeliveryWithUserStatus {
  delivery: IDelivery
  user: IUser
  userStatus: TransportRequestStatus
}

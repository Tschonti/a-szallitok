import { TransportRequestStatus } from '../model/TransportRequest'
import { IDelivery } from './delivery'

export interface DeliveryWithUserStatus {
  delivery: IDelivery
  userStatus: TransportRequestStatus
}

import { Document, model, Model, Schema } from 'mongoose'

export enum TransportRequestStatus {
  PENDING = 'PENDING',
  REJECTED = 'REJECTED',
  ACCEPTED = 'ACCEPTED'
}

interface ITransportRequest {
  delivery: Schema.Types.ObjectId
  user: Schema.Types.ObjectId
  status: TransportRequestStatus
}

export interface TransportRequestDoc extends ITransportRequest, Document {}

interface TransportRequestModel extends Model<TransportRequestDoc> {
  build: (attr: ITransportRequest) => TransportRequestDoc
}

const transportRequestSchema = new Schema({
  delivery: {
    type: Schema.Types.ObjectId,
    ref: 'Delivery'
  },
  user: {
    type: Schema.Types.ObjectId,
    ref: 'User'
  },
  status: {
    type: String,
    enum: [TransportRequestStatus.ACCEPTED, TransportRequestStatus.REJECTED, TransportRequestStatus.PENDING],
    default: TransportRequestStatus.PENDING
  }
})
transportRequestSchema.set('timestamps', true)
transportRequestSchema.statics.build = (attr: ITransportRequest) => new TransportRequest(attr)

const TransportRequest = model<TransportRequestDoc, TransportRequestModel>('TransportRequest', transportRequestSchema)

export { TransportRequest }

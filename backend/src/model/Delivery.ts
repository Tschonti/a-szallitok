import { Document, model, Model, Schema } from 'mongoose'
import { IDelivery } from '../types/delivery'

enum DeliveryStatus {
  UNASSIGNED = 'UNASSIGNED',
  PENDING = 'PENDING',
  ASSIGNED = 'ASSIGNED',
  IN_TRANSIT = 'IN_TRANSIT',
  DELIVERED = 'DELIVERED'
}

interface DeliveryDoc extends IDelivery, Document {}

interface DeliveryModel extends Model<DeliveryDoc> {
  build: (attr: IDelivery) => DeliveryDoc
}

const deliverySchema = new Schema({
  title: {
    type: String,
    required: true
  },
  description: {
    type: String,
    required: true
  },
  sourceLocation: {
    type: Schema.Types.ObjectId,
    ref: 'Location',
    required: true
  },
  destinationLocation: {
    type: Schema.Types.ObjectId,
    ref: 'Location',
    required: true
  },
  clientUser: {
    type: Schema.Types.ObjectId,
    ref: 'User',
    required: true
  },
  transporterUser: {
    type: Schema.Types.ObjectId,
    ref: 'User',
    required: false
  },
  pickUpFrom: {
    type: Date,
    required: true
  },
  pickUpUntil: {
    type: Date,
    required: true
  },
  price: {
    type: Number,
    required: true
  },
  clientRating: {
    type: Number,
    required: false
  },
  transporterRating: {
    type: Number,
    required: false
  },
  capacity: {
    weight: {
      type: Number,
      required: true
    },
    height: {
      type: Number,
      required: true
    },
    length: {
      type: Number,
      required: true
    },
    width: {
      type: Number,
      required: true
    }
  },
  status: {
    type: String,
    enum: [DeliveryStatus.UNASSIGNED, DeliveryStatus.PENDING, DeliveryStatus.IN_TRANSIT, DeliveryStatus.DELIVERED],
    default: DeliveryStatus.UNASSIGNED,
    required: true
  },
  pictureUrl: {
    type: String,
    required: false
  }
})
deliverySchema.set('timestamps', true)
deliverySchema.statics.build = (attr: IDelivery) => new Vehicle(attr)

const Vehicle = model<DeliveryDoc, DeliveryModel>('Vehicle', deliverySchema)

export { Vehicle }

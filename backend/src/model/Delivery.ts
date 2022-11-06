import { Document, model, Model, Schema } from 'mongoose'
import { IDelivery } from '../types/delivery'

export enum DeliveryStatus {
  UNASSIGNED = 'UNASSIGNED',
  PENDING = 'PENDING',
  ASSIGNED = 'ASSIGNED',
  IN_TRANSIT = 'IN_TRANSIT',
  DELIVERED = 'DELIVERED'
}

export interface DeliveryDoc extends IDelivery, Document {}

interface DeliveryModel extends Model<DeliveryDoc> {
  build: (attr: IDelivery) => DeliveryDoc
}

const locationSchema = new Schema({
  coordinate: {
    longitude: {
      type: Number,
      required: true
    },
    latitude: {
      type: Number,
      required: true
    }
  },
  country: {
    type: String,
    required: true
  },
  postalCode: {
    type: Number,
    required: true
  },
  city: {
    type: String,
    required: true
  },
  address: {
    type: String,
    required: true
  }
})

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
    type: locationSchema,
    required: true
  },
  destinationLocation: {
    type: locationSchema,
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
    enum: [
      DeliveryStatus.UNASSIGNED,
      DeliveryStatus.PENDING,
      DeliveryStatus.IN_TRANSIT,
      DeliveryStatus.DELIVERED,
      DeliveryStatus.ASSIGNED
    ],
    default: DeliveryStatus.UNASSIGNED,
    required: true
  },
  pictureUrl: {
    type: String,
    required: false
  }
})
deliverySchema.set('timestamps', true)
deliverySchema.statics.build = (attr: IDelivery) => new Delivery(attr)

const Delivery = model<DeliveryDoc, DeliveryModel>('Delivery', deliverySchema)

export { Delivery }

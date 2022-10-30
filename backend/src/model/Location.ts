import { Document, model, Model, Schema } from 'mongoose'
import { ILocation } from '../types/location'

export interface LocationDoc extends ILocation, Document {}

interface LocationModel extends Model<LocationDoc> {
  build: (attr: ILocation) => LocationDoc
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
locationSchema.set('timestamps', true)
locationSchema.statics.build = (attr: ILocation) => new Location(attr)

const Location = model<LocationDoc, LocationModel>('Location', locationSchema)

export { Location }

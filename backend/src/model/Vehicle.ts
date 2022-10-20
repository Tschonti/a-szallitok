import { Document, model, Model, Schema } from 'mongoose'
import { IVehicle } from '../types/vehicle'

interface VehicleDoc extends IVehicle, Document {}

interface VehicleModel extends Model<VehicleDoc> {
  build: (attr: IVehicle) => VehicleDoc
}

const vehicleSchema = new Schema({
  plateNumber: {
    type: String,
    required: true
  },
  type: {
    type: String,
    required: true
  },
  yearOfManufacturing: {
    type: Number,
    required: true
  },
  location: {
    type: String,
    required: false
  },
  maxCapacity: {
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
  pictureUrl: {
    type: String,
    required: false
  }
})
vehicleSchema.set('timestamps', true)
vehicleSchema.statics.build = (attr: IVehicle) => new Vehicle(attr)

const Vehicle = model<VehicleDoc, VehicleModel>('Vehicle', vehicleSchema)

export { Vehicle }

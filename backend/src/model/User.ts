import { Document, model, Model, Schema } from 'mongoose'
import { IUser } from '../types/user'

interface UserDoc extends IUser, Document {}

interface UserModel extends Model<UserDoc> {
  build: (attr: IUser) => UserDoc
}

const userSchema = new Schema({
  name: {
    type: String,
    required: true
  },
  email: {
    type: String,
    required: true
  },
  googleToken: {
    type: String,
    required: true
  },
  phoneNumber: {
    type: String,
    required: false
  },
  _vehicle: {
    type: Schema.Types.ObjectId,
    ref: 'Vehicle',
    required: false
  },
  isAdmin: {
    type: Boolean,
    required: true,
    default: false
  },
  profilePictureUrl: {
    type: String,
    required: false
  }
})
userSchema.set('timestamps', true)
userSchema.statics.build = (attr: IUser) => new User(attr)

const User = model<UserDoc, UserModel>('User', userSchema)

export { User }

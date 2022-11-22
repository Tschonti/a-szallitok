import { User, UserDoc } from '../model/User'
import { NextFunction, Request, Response } from 'express'
import { TransportRequest } from '../model/TransportRequest'
import { Delivery, DeliveryDoc, DeliveryStatus } from '../model/Delivery'

interface AggregationResult {
  average: number
  count: number
}

function validateAggregationResult (arr: AggregationResult[]): AggregationResult {
  if (arr.length === 0) {
    return { average: 0, count: 0 }
  }
  return arr[0]
}

export const getUser = async (req: Request, res: Response, next: NextFunction) => {
  res.locals.dbUser = await User.findById(req.params.id).exec()
  if (res.locals.dbUser == null) {
    return res.sendStatus(404)
  }
  return next()
}

export const calculateRating = async (req: Request, res: Response, next: NextFunction) => {
  const user = res.locals.dbUser
  if (user == null) {
    return res.sendStatus(404)
  }
  const clientAggregate: AggregationResult = validateAggregationResult(await Delivery
    .aggregate(
      [{
        $match: {
          clientUser: user._id,
          clientRating: { $exists: true }
        }
      }, {
        $group: {
          _id: null,
          average: {
            $avg: '$clientRating'
          },
          count: { $sum: 1 }
        }
      }]).exec())
  const transporterAggregate: AggregationResult = validateAggregationResult(await Delivery
    .aggregate(
      [{
        $match: {
          transporterUser: user._id,
          transporterRating: { $exists: true }
        }
      }, {
        $group: {
          _id: null,
          average: {
            $avg: '$transporterRating'
          },
          count: { $sum: 1 }
        }
      }]).exec())
  const avgRating = (clientAggregate.count * clientAggregate.average +
    transporterAggregate.count * transporterAggregate.average) /
  (clientAggregate.count + transporterAggregate.count) || 0
  return res.status(200).send({ ...user._doc, avgRating })
}

export const generateToplist = async (req: Request, res: Response) => {
  const aggr = await Delivery
    .aggregate(
      [{
        $match: {
          status: 'DELIVERED'
        }
      },
      {
        $group: {
          _id: '$transporterUser',
          moneyEarned: {
            $sum: '$price'
          },
          deliveriesCompleted: { $sum: 1 }
        }
      }]).sort({ moneyEarned: -1, deliveriesCompleted: -1 }).limit(10).exec()
  return res.status(200).send(await User.populate(aggr, { path: '_id' }))
}

export const updateUser = async (req: Request, res: Response) => {
  const user = await User.findByIdAndUpdate(res.locals.dbUser?._id.toString(),
    { phoneNumber: req.body.phoneNumber }).exec()
  if (user == null) {
    return res.sendStatus(404)
  }
  return res.status(201).send(user)
}

export const checkIfAdmin = (req: Request, res: Response, next: NextFunction) => {
  if (!res.locals.dbUser) {
    return res.sendStatus(401)
  }
  if (!res.locals.dbUser.isAdmin) {
    return res.sendStatus(403)
  }
  return next()
}

export const deleteLoggedInUser = async (req: Request, res: Response) => {
  try {
    await Delivery.deleteMany({
      $or: [
        { clientUser: res.locals.dbUser?._id },
        { transporterUser: res.locals.dbUser?._id, status: DeliveryStatus.DELIVERED }
      ]
    })
    await Delivery.updateMany(
      { transporterUser: res.locals.dbUser?._id },
      { transporterUser: undefined, status: DeliveryStatus.UNASSIGNED })
    await TransportRequest.deleteMany({ user: req.params.id })
    const user = await User.findByIdAndDelete(res.locals.dbUser?._id.toString()).exec()
    if (user == null) {
      return res.sendStatus(404)
    }
    return res.status(200).send(user)
  } catch (e) {
    console.error(e)
    return res.sendStatus(404)
  }
}

export const deleteParamUser = async (req: Request, res: Response) => {
  await Delivery.deleteMany({
    $or: [
      { clientUser: req.params.id },
      { transporterUser: req.params.id, status: DeliveryStatus.DELIVERED }
    ]
  })
  await Delivery.updateMany(
    { transporterUser: req.params.id },
    { transporterUser: undefined, status: DeliveryStatus.UNASSIGNED })
  await TransportRequest.deleteMany({ user: req.params.id })
  const user = await User.findByIdAndDelete(req.params.id).exec()
  if (user == null) {
    return res.sendStatus(404)
  }
  return res.status(200).send(user)
}

export const getRequestedJobs = async (req: Request, res: Response) => {
  return res.status(200).send(await TransportRequest
    .find({ user: res.locals.dbUser?._id })
    .populate<{delivery: DeliveryDoc}>('delivery').exec())
}

export const getJobRequests = async (req: Request, res: Response) => {
  const allRequests = await TransportRequest
    .find({})
    .populate<{delivery: DeliveryDoc, user: UserDoc}>(['delivery', 'user'])
    .exec()
  return res.status(200).send(
    allRequests.filter(r => r.delivery.clientUser.toString() === res.locals.dbUser?._id.toString())
  )
}

export const getUsers = async (req: Request, res: Response) => {
  return res.status(200).send(await User.find({}).exec())
}

export const promote = async (req: Request, res: Response) => {
  const user = await User.findById(req.params.id).exec()
  if (user == null) {
    return res.sendStatus(404)
  }
  user.isAdmin = true
  await user.save()
  return res.status(200).send(user)
}

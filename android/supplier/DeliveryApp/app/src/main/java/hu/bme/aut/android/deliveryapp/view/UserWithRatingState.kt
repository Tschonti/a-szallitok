package hu.bme.aut.android.deliveryapp.view

import hu.bme.aut.android.deliveryapp.model.User
import hu.bme.aut.android.deliveryapp.model.UserWithRating

sealed class UserWithRatingState {
    object inProgress : UserWithRatingState()
    data class userResponseSuccess(val data: UserWithRating) : UserWithRatingState()
    data class userResponseError(val exceptionMsg: String) : UserWithRatingState()
}
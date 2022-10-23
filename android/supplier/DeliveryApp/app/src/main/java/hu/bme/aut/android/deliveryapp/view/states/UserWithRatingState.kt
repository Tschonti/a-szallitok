package hu.bme.aut.android.deliveryapp.view.states

import hu.bme.aut.android.deliveryapp.model.UserWithRating

sealed class UserWithRatingState {
    object inProgress : UserWithRatingState()
    data class userResponseSuccess(val data: UserWithRating) : UserWithRatingState()
    data class userResponseError(val exceptionMsg: String) : UserWithRatingState()
}
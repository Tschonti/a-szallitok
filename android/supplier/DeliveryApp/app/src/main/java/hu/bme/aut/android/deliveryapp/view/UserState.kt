package hu.bme.aut.android.deliveryapp.view

import hu.bme.aut.android.deliveryapp.model.Delivery
import hu.bme.aut.android.deliveryapp.model.User

sealed class UserState {
    object inProgress : UserState()
    data class userResponseSuccess(val data: User) : UserState()
    data class userResponseError(val exceptionMsg: String) : UserState()
}
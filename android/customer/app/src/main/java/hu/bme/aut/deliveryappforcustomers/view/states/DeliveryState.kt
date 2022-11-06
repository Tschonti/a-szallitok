package hu.bme.aut.deliveryappforcustomers.view.states

import hu.bme.aut.android.deliveryapp.model.Delivery

sealed class DeliveryState {
    object inProgress : DeliveryState()
    data class deliveriesResponseSuccess(val data: Delivery) : DeliveryState()
    data class deliveriesResponseError(val exceptionMsg: String) : DeliveryState()
}
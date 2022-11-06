package hu.bme.aut.android.deliveryapp.view.states

import hu.bme.aut.android.deliveryapp.model.Delivery
import hu.bme.aut.android.deliveryapp.model.DeliveryInProgress

sealed class DeliveryInProgressState {
    object inProgress : DeliveryInProgressState()
    data class deliveriesResponseSuccess(val data: List<DeliveryInProgress>) : DeliveryInProgressState()
    data class deliveriesResponseError(val exceptionMsg: String) : DeliveryInProgressState()
}
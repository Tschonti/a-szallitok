package hu.bme.aut.android.deliveryapp.view

import hu.bme.aut.android.deliveryapp.model.Delivery
import hu.bme.aut.android.deliveryapp.model.JobDetails

sealed class DeliveryState {
    object inProgress : DeliveryState()
    data class deliveriesResponseSuccess(val data: List<Delivery> ) : DeliveryState()
    data class deliveriesResponseError(val exceptionMsg: String) : DeliveryState()
}
package hu.bme.aut.deliveryappforcustomers.view.states

import hu.bme.aut.android.deliveryapp.model.Delivery

sealed class DeliveryListState {
    object inProgress : DeliveryListState()
    data class deliveriesResponseSuccess(val data: List<Delivery>) : DeliveryListState()
    data class deliveriesResponseError(val exceptionMsg: String) : DeliveryListState()
}
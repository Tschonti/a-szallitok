package hu.bme.aut.deliveryappforcustomers.model

import hu.bme.aut.android.deliveryapp.model.Delivery

sealed class DUSstate {
    object inProgress : DUSstate()
    data class deliveriesResponseSuccess(val data: Delivery) : DUSstate()
    data class deliveriesResponseError(val exceptionMsg: String) : DUSstate()
}
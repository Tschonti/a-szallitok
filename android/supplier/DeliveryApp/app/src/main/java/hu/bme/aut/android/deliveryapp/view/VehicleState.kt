package hu.bme.aut.android.deliveryapp.view

import hu.bme.aut.android.deliveryapp.model.User
import hu.bme.aut.android.deliveryapp.model.Vehicle

sealed class VehicleState {
    object inProgress : VehicleState()
    data class vehicleResponseSuccess(val data: Vehicle) : VehicleState()
    data class vehicleResponseError(val exceptionMsg: String) : VehicleState()
}
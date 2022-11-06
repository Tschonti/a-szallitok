package hu.bme.aut.android.deliveryapp.model

import android.location.Location
import java.io.Serializable

data class Vehicle(
    val pictureUrl: String?,
    val yearOfManufacturing: Int,
    val location: String,
    var _id: String?, //TODO: should be val
    val plateNumber: String,
    val type: String,
    val maxCapacity: Capacity,
) : Serializable

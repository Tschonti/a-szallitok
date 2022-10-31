package hu.bme.aut.android.deliveryapp.model

import android.location.Location
import java.io.Serializable

data class Vehicle(
    val maxHeight: Float,
    val pictureUrl: String,
    val yearOfManufacturing: Int,
    val location: String,
    var id: String, //TODO: should be val
    val maxWeight: Float,
    val plateNumber: String,
    val type: String,
    val maxLength: Float,
    val maxWidth: Float,
) : Serializable

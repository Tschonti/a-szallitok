package hu.bme.aut.android.deliveryapp.model

import android.location.Location

data class Vehicle(
    val maxHeight: Float,
    val pictureUrl: String,
    val yearOfManufacturing: Int,
    val location: String,
    val id: String,
    val maxWeight: Float,
    val plateNumber: String,
    val type: String,
    val maxLength: Float,
    val maxWidth: Float,
)

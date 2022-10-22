package hu.bme.aut.android.deliveryapp.model

import android.location.Location

data class Vehicle(
    val maxHeight: Int,
    val pictureUrl: String,
    val yearOfManufacturing: Int,
    val location: String,
    val id: Int,
    val maxWeight: Int,
    val plateNumber: String,
    val type: String,
    val maxLength: Int,
    val maxWidth: Int,
)

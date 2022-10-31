package hu.bme.aut.android.deliveryapp.model

import android.location.Location

data class Delivery(
    val clientUserId: String,
    val transporterUserId: String,
    val pictureUrl: String,
    val length: Float,
    val description: String,
    val destinationLocation: Location,
    val weight: Float,
    val title: String,
    val createdAt: String,
    val clientRating: Float,
    val pickUpUntil: String,
    val price: Float,
    val width: Float,
    val sourceLocation: Location,
    val transporterRating: Float,
    val id: String,
    val pickUpFrom: String,
    val height: Float,
    var status: String, //TODO: should be val
    val updatedAt: String
)

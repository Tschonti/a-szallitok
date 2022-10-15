package hu.bme.aut.android.deliveryapp.model

import android.location.Location

data class Delivery(
    val clientUserId: Int,
    val transporterUserId: Int,
    val pictureUrl: String,
    val length: Int,
    val description: String,
    val destinationLocation: String,
    val weight: Int,
    val title: String,
    val createdAt: String,
    val clientRating: Int,
    val pickUpUntil: String,
    val price: Int,
    val width: Int,
    val sourceLocation: Location,
    val transporterRating: Int,
    val id: Int,
    val pickUpFrom: String,
    val height: Int,
    val status: String,
    val updatedAt: String
)

package hu.bme.aut.android.deliveryapp.model

import java.io.Serializable

data class Delivery(
    val clientUser: String,
    val transporterUser: String,
    val pictureUrl: String,
    val description: String,
    val destinationLocation: Location,
    val title: String,
    val clientRating: Float,
    val pickUpUntil: String,
    val price: Float,
    val sourceLocation: Location,
    val transporterRating: Float,
    val createdAt: String,
    val _id: String,
    val pickUpFrom: String,
    var status: String,
    val updatedAt: String,
    val capacity: Capacity,
) : Serializable

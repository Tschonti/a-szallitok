package hu.bme.aut.android.deliveryapp.model

import hu.bme.aut.deliveryappforcustomers.model.Capacity
import hu.bme.aut.deliveryappforcustomers.model.DeliveryStatus
import hu.bme.aut.deliveryappforcustomers.model.Location
import java.io.Serializable

data class Delivery(
    val clientUser: String,
    val transporterUser: String? = null,
    val pictureUrl: String? = null,
    val description: String,
    val destinationLocation: Location,
    val title: String,
    val clientRating: Float? = null,
    val pickUpUntil: String,
    val price: Float,
    val sourceLocation: Location,
    val transporterRating: Float? = null,
    val createdAt: String,
    val _id: String? = null,
    val pickUpFrom: String,
    var status: DeliveryStatus? = null,
    val updatedAt: String? = null,
    val capacity: Capacity? = null,
) : Serializable

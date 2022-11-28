package hu.bme.aut.deliveryappforcustomers.model

import hu.bme.aut.android.deliveryapp.model.Delivery
import hu.bme.aut.android.deliveryapp.model.User
import java.io.Serializable

data class DeliveryWithUserAndStatus(
    val delivery: Delivery,
    val user: User?,
    val userStatus: String?,
) : Serializable
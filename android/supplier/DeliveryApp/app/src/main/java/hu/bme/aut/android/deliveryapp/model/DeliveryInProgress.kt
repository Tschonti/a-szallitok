package hu.bme.aut.android.deliveryapp.model

import java.io.Serializable

data class DeliveryInProgress(
    val delivery: Delivery,
    val status: InProgressStatus,
) : Serializable

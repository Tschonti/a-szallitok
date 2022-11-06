package hu.bme.aut.android.deliveryapp.model

data class DeliveryInProgress(
    val delivery: Delivery,
    val status: DeliveryStatus,
)

package hu.bme.aut.android.deliveryapp.model

import java.io.Serializable

data class JobDetails(
    val title: String?,
    val description: String?,
    val clientId: String,
    var transporterId: String?,
    val deliveryId: String?,
    val clientName: String?,
    val clientAvgRating: Float?,
    val imPaths: String?,
    val deliveryDate: String?,
    val deliveryCost: Int?,
    val deliveryLocation: Location?,
) : Serializable

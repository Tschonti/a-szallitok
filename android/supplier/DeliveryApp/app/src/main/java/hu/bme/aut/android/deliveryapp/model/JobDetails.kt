package hu.bme.aut.android.deliveryapp.model

import java.io.Serializable

data class JobDetails(
    val title: String?,
    val description: String?,
    var clientId: String, //TODO: should be val
    var transporterId: String?,
    var deliveryId: String, //TODO: should be val
    val clientName: String?,
    val clientAvgRating: Float?,
    val imPaths: String?,
    val deliveryDate: String?,
    val deliveryCost: Int?,
    val deliveryLocation: Location?,
) : Serializable

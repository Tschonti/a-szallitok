package hu.bme.aut.android.deliveryapp.model

import java.io.Serializable

data class JobDetails(
    val clientName: String?,
    val transporterName: String?,
    val imPaths: String?,
    val avgRating: Float?,
    val deliveryDate: String?,
    val deliveryCost: Int?,
    val deliveryLocation: Location?,
    val deliveryId: String?,
    val title: String?,
    val description: String?,
) : Serializable

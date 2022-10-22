package hu.bme.aut.android.deliveryapp.model

import java.io.Serializable

data class JobDetails(
    val name: String,
    val imPaths: String,
    val avgRating: Float,
    val deliveryDate: String,
    val deliveryCost: String,
    val deliveryLocation: Location
) : Serializable

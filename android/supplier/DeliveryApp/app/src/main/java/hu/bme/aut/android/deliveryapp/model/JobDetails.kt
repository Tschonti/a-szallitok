package hu.bme.aut.android.deliveryapp.model

import android.location.Location

data class JobDetails(
    val name: String,
    val imPaths: String,
    val avgRating: Int,
    val deliveryDate: String,
    val deliveryCost: String,
    val deliveryLocation: Location
)

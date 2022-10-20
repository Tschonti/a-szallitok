package hu.bme.aut.android.deliveryapp.model

data class Location(
    val country: String,
    val coordinate: Coordinate,
    val address: String,
    val city: String,
    val postalCode: Int,
)

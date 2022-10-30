package hu.bme.aut.android.deliveryapp.model

data class User(
    val id: String,
    val profilePictureUrl: String,
    val name: String,
    val googletoken: String,
    val phoneNumber: String,
    val vehicleId: String,
    val isAdmin: Boolean,
    val email: String,
    val avgRating: Float,
)
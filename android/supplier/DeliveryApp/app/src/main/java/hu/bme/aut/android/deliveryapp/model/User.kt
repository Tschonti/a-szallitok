package hu.bme.aut.android.deliveryapp.model

data class User(
    val profilePictureUrl: String,
    val name: String,
    val googletoken: String,
    val phoneNumber: String,
    val vehicleId: Int,
    val isAdmin: Boolean,
    val email: String
)
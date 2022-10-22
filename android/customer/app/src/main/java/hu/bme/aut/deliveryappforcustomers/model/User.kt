package hu.bme.aut.android.deliveryapp.model

data class User(
    val profilePictureUrl: String,
    val firstName: String,
    val lastName: String,
    val googletoken: String,
    val phoneNumber: String,
    val id: Int,
    val vehicleId: Int,
    val isAdmin: Boolean,
    val email: String
)
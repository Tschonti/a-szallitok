package hu.bme.aut.android.deliveryapp.model

data class User(
    val _id: String,
    val profilePictureUrl: String,
    val name: String,
    val googletoken: String,
    val phoneNumber: String,
    var vehicle: String?, //TODO: Should be val, but for debug purposes it's var
    val isAdmin: Boolean,
    val email: String,
    val avgRating: Float,
)
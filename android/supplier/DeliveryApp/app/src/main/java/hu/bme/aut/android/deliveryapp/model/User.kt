package hu.bme.aut.android.deliveryapp.model

data class User(
    var id: String, //TODO: Should be val, but for debug purposes it's var
    val profilePictureUrl: String,
    val name: String,
    val googletoken: String,
    val phoneNumber: String,
    var vehicleId: String?, //TODO: Should be val, but for debug purposes it's var
    val isAdmin: Boolean,
    val email: String,
    val avgRating: Float,
)
package hu.bme.aut.android.deliveryapp.repository

import hu.bme.aut.android.deliveryapp.model.User

object CurrentUser {
    private var _token: String? = null
    var token: String
        get() = _token ?: ""
        set(value) { _token = value }

    private var _user: User? = null
    var user: User
        get() = _user ?: User("", "", "", "","", "", false, "")
        set(value) { _user = value }
}
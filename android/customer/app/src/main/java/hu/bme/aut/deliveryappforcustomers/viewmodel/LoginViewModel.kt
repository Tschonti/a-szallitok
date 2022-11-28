package hu.bme.aut.deliveryappforcustomers.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import hu.bme.aut.deliveryappforcustomers.repository.ApiRepository
import hu.bme.aut.deliveryappforcustomers.view.states.UserState


class LoginViewModel : ViewModel() {
    private val apiRepository = ApiRepository()

    fun loginUser(token: String): LiveData<UserState> {
        return apiRepository.loginUser(token)
    }
}
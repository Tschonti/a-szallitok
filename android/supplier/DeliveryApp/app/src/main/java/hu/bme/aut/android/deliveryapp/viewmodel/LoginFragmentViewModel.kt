package hu.bme.aut.android.deliveryapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import hu.bme.aut.android.deliveryapp.repository.ApiRepository
import hu.bme.aut.android.deliveryapp.view.states.UserState

class LoginFragmentViewModel : ViewModel() {
    private val apiRepository = ApiRepository()

    fun loginUser(token: String): LiveData<UserState> {
        return apiRepository.loginUser(token)
    }
}
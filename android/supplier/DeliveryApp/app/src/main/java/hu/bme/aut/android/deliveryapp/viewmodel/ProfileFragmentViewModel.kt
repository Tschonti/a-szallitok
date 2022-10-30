package hu.bme.aut.android.deliveryapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import hu.bme.aut.android.deliveryapp.repository.ApiRepository
import hu.bme.aut.android.deliveryapp.view.states.UserState

class ProfileFragmentViewModel : ViewModel() {
    private val apiRepository = ApiRepository()

    private lateinit var userData: LiveData<UserState>

    fun getUserData(id: String): LiveData<UserState> {
        if (!::userData.isInitialized) {
            userData = apiRepository.getUserData(id)
        }
        return userData
    }
}
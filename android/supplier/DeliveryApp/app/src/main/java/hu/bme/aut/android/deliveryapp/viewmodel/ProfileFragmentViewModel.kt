package hu.bme.aut.android.deliveryapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import hu.bme.aut.android.deliveryapp.repository.ApiRepository
import hu.bme.aut.android.deliveryapp.view.states.UserState
import hu.bme.aut.android.deliveryapp.view.states.UserWithRatingState

class ProfileFragmentViewModel : ViewModel() {
    private val apiRepository = ApiRepository()

    private lateinit var userData: LiveData<UserState>
    private lateinit var userRating: LiveData<UserWithRatingState>

    fun getUserData(id: Int): LiveData<UserState> {
        if (!::userData.isInitialized) {
            userData = apiRepository.getUserData(id)
        }
        return userData
    }

    fun getUserRating(id: Int): LiveData<UserWithRatingState> {
        if (!::userRating.isInitialized) {
            userRating = apiRepository.getUserRating(id)
        }
        return userRating
    }
}
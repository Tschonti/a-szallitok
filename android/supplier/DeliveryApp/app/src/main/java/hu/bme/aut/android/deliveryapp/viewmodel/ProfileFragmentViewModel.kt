package hu.bme.aut.android.deliveryapp.viewmodel

import android.service.autofill.UserData
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import hu.bme.aut.android.deliveryapp.model.User
import hu.bme.aut.android.deliveryapp.model.UserWithRating
import hu.bme.aut.android.deliveryapp.repository.ApiRepository
import hu.bme.aut.android.deliveryapp.view.JobDetailState
import hu.bme.aut.android.deliveryapp.view.UserState
import hu.bme.aut.android.deliveryapp.view.UserWithRatingState

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
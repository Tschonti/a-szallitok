package hu.bme.aut.android.deliveryapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import hu.bme.aut.android.deliveryapp.repository.ApiRepository
import hu.bme.aut.android.deliveryapp.view.states.UserWithRatingState

class JobDetailsFragmentViewModel : ViewModel() {
    private val apiRepository = ApiRepository()

    private lateinit var userRating: LiveData<UserWithRatingState>

    fun getUserRating(id: String): LiveData<UserWithRatingState> {
        if (!::userRating.isInitialized) {
            userRating = apiRepository.getUserRating(id)
        }
        return userRating
    }
}
package hu.bme.aut.android.deliveryapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import hu.bme.aut.android.deliveryapp.repository.ApiRepository
import hu.bme.aut.android.deliveryapp.view.JobDetailState
import hu.bme.aut.android.deliveryapp.view.UserState

class ProfileFragmentViewModel : ViewModel() {

    private val apiRepository = ApiRepository()

    fun getUserData(id: Int): LiveData<UserState> {
        return apiRepository.getUserData(id)
    }
}
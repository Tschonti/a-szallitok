package hu.bme.aut.android.deliveryapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import hu.bme.aut.android.deliveryapp.repository.ApiRepository
import hu.bme.aut.android.deliveryapp.view.states.UserState

class JobDetailsFragmentViewModel : ViewModel() {
    private val apiRepository = ApiRepository()

    fun getUserData(id: String): LiveData<UserState> {
        return  apiRepository.getUserData(id)
    }
}
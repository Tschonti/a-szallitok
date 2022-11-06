package hu.bme.aut.android.deliveryapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import hu.bme.aut.android.deliveryapp.model.Delivery
import hu.bme.aut.android.deliveryapp.repository.ApiRepository
import hu.bme.aut.android.deliveryapp.repository.CurrentUser.user
import hu.bme.aut.android.deliveryapp.view.states.DeliveryState
import hu.bme.aut.android.deliveryapp.view.states.UserState

class InProgressJobsDetailsFragmentViewModel : ViewModel() {

    private val apiRepository = ApiRepository()

    fun getUserData(userId: String): LiveData<UserState> {
        return apiRepository.getUserData(userId)
    }

    fun getDeliveryData(deliveryId: String): LiveData<DeliveryState> {
        return apiRepository.getDelivery(deliveryId)
    }

    fun markJobAsReady(d: Delivery): LiveData<DeliveryState> {
        return apiRepository.markJobAsReady(d)
    }

    fun markDeliveryAsInTransit(d: Delivery): LiveData<DeliveryState> {
        return apiRepository.markDeliveryAsInTransit(d)
    }

    fun rateClient(deliveryId: String, rating: Int): LiveData<DeliveryState> {
        return apiRepository.rateClient(deliveryId, rating)
    }
}
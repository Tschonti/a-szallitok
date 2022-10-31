package hu.bme.aut.android.deliveryapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import hu.bme.aut.android.deliveryapp.model.Delivery
import hu.bme.aut.android.deliveryapp.repository.ApiRepository
import hu.bme.aut.android.deliveryapp.view.states.DeliveryState
import hu.bme.aut.android.deliveryapp.view.states.JobDetailState
import hu.bme.aut.android.deliveryapp.view.states.UserState

class InProgressJobsDetailsFragmentViewModel : ViewModel() {

    private val apiRepository = ApiRepository()

    private lateinit var user: LiveData<UserState>
    private lateinit var delivery: LiveData<DeliveryState>

    fun getUserData(userId: String): LiveData<UserState> {
        if (!::user.isInitialized) {
            user = apiRepository.getUserData(userId)
        }
        return user
    }

    fun getDeliveryData(deliveryId: String): LiveData<DeliveryState> {
        if (!::delivery.isInitialized) {
            delivery = apiRepository.getDelivery(deliveryId)
        }
        return delivery
    }

    fun markJobAsReady(d: Delivery): LiveData<DeliveryState> {
        if (!::delivery.isInitialized) {
            delivery = apiRepository.markJobAsReady(d)
        }
        return delivery
    }

    fun markDeliveryAsCancelled(d: Delivery): LiveData<DeliveryState> {
        if (!::delivery.isInitialized) {
            delivery = apiRepository.markDeliveryAsCancelled(d)
        }
        return delivery
    }
}
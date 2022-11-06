package hu.bme.aut.deliveryappforcustomers.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hu.bme.aut.android.deliveryapp.model.Delivery
import hu.bme.aut.deliveryappforcustomers.repository.ApiRepository
import hu.bme.aut.deliveryappforcustomers.view.JobDetailState
import hu.bme.aut.deliveryappforcustomers.view.states.DeliveryState

class NewTransportViewModel : ViewModel() {
    private val apiRepository = ApiRepository()

    fun createNewTransport(delivery: Delivery): MutableLiveData<Delivery> {
        return apiRepository.createNewTransport(delivery)
    }
}
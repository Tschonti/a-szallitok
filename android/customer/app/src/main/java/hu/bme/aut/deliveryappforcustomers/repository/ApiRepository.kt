package hu.bme.aut.deliveryappforcustomers.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import hu.bme.aut.android.deliveryapp.model.Delivery
import hu.bme.aut.deliveryappforcustomers.api.DeliveryApi
import hu.bme.aut.deliveryappforcustomers.view.JobDetailState
import hu.bme.aut.deliveryappforcustomers.view.states.DeliveryState

class ApiRepository {
    fun getUserHistory(id: Int): MutableLiveData<JobDetailState> {
        return DeliveryApi.getUserHistory(id)
    }

    fun getActiveTransports(userId: Int): LiveData<JobDetailState> {
        return DeliveryApi.getActiveTransports(userId)
    }

    fun createNewTransport(delivery: Delivery): MutableLiveData<Delivery> {
        return DeliveryApi.createNewTransport(delivery)
    }

    /*fun loginUser(token: String): MutableLiveData<DeliveryState> { //TODO: change to UserState
        //return DeliveryApi.loginUser(token)
    }*/
}
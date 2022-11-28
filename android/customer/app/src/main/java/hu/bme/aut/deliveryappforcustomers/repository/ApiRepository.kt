package hu.bme.aut.deliveryappforcustomers.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import hu.bme.aut.android.deliveryapp.model.Delivery
import hu.bme.aut.deliveryappforcustomers.api.DeliveryApi
import hu.bme.aut.deliveryappforcustomers.model.DeliveryWithUserAndStatus
import hu.bme.aut.deliveryappforcustomers.view.JobDetailState
import hu.bme.aut.deliveryappforcustomers.view.states.UserState

class ApiRepository {
    fun getUserHistory(id: Int): MutableLiveData<JobDetailState> {
        return DeliveryApi.getUserHistory(id)
    }

    fun getJobRequests(): MutableLiveData<List<DeliveryWithUserAndStatus>?> {
        val resultData = DeliveryApi.getJobRequests()
        Log.d("BODYSIZE in repository", resultData.value?.size.toString())
        return resultData
    }

    fun createNewTransport(delivery: Delivery): MutableLiveData<Delivery> {
        return DeliveryApi.createNewTransport(delivery)
    }

    fun loginUser(token: String): MutableLiveData<UserState> {
        return DeliveryApi.loginUser(token)
    }
}
package hu.bme.aut.deliveryappforcustomers.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import hu.bme.aut.deliveryappforcustomers.api.DeliveryApi
import hu.bme.aut.deliveryappforcustomers.view.JobDetailState

class ApiRepository {
    fun getUserHistory(id: Int): MutableLiveData<JobDetailState> {
        return DeliveryApi.getUserHistory(id)
    }

    fun getActiveTransports(userId: Int): LiveData<JobDetailState> {
        return DeliveryApi.getActiveTransports(userId)
    }
}
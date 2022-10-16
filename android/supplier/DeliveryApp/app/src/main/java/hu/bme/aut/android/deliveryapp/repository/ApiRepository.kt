package hu.bme.aut.android.deliveryapp.repository

import androidx.lifecycle.MutableLiveData
import hu.bme.aut.android.deliveryapp.api.DeliveryApi
import hu.bme.aut.android.deliveryapp.model.Delivery
import hu.bme.aut.android.deliveryapp.view.DeliveryState
import hu.bme.aut.android.deliveryapp.view.JobDetailState

class ApiRepository {
    fun getUserHistory(id: Int): MutableLiveData<JobDetailState> {
        return DeliveryApi.getUserHistory(id)
    }

    fun getAvailableJobs(id: Int): MutableLiveData<JobDetailState> {
        return DeliveryApi.getAvailableJobs(id)
    }

    fun getDeliveries (id: Int): MutableLiveData<DeliveryState> {
        return DeliveryApi.getDeliveryData(id)
    }
}
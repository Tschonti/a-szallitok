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

    fun getAvailableJobs(status: String?, sourceCity: String?, destinationCity: String?, price: Int?, transporterId: Int?, date: String?): MutableLiveData<JobDetailState> {
        return DeliveryApi.getAvailableJobs(sourceCity, destinationCity, price, date)
    }

    fun getDeliveries (id: Int): MutableLiveData<DeliveryState> {
        return DeliveryApi.getDeliveryData(id)
    }

    fun getJobsInProgress(id: Int): MutableLiveData<JobDetailState> {
        return DeliveryApi.getJobsInProgress(id)
    }
}
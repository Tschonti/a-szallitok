package hu.bme.aut.android.deliveryapp.repository

import androidx.lifecycle.MutableLiveData
import hu.bme.aut.android.deliveryapp.api.DeliveryApi
import hu.bme.aut.android.deliveryapp.model.Delivery
import hu.bme.aut.android.deliveryapp.view.DeliveryState
import hu.bme.aut.android.deliveryapp.view.JobDetailState
import hu.bme.aut.android.deliveryapp.view.UserState
import hu.bme.aut.android.deliveryapp.view.VehicleState

class ApiRepository {
    fun getUserHistory(id: Int): MutableLiveData<JobDetailState> {
        return DeliveryApi.getUserHistory(id)
    }

    fun getAvailableJobs(sourceCity: String?, destinationCity: String?, price: Int?, date: String?): MutableLiveData<JobDetailState> {
        return DeliveryApi.getAvailableJobs(sourceCity, destinationCity, price, date)
    }

    fun getDeliveries (id: Int): MutableLiveData<DeliveryState> {
        return DeliveryApi.getDeliveryData(id)
    }

    fun getJobsInProgress(id: Int): MutableLiveData<JobDetailState> {
        return DeliveryApi.getJobsInProgress(id)
    }

    fun getUserData(id: Int): MutableLiveData<UserState> {
        return DeliveryApi.getUserData(id)
    }

    fun getVehicleData(id: Int): MutableLiveData<VehicleState> {
        return DeliveryApi.getVehicleData(id)
    }
}
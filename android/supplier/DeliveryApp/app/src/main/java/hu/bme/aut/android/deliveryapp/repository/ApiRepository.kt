package hu.bme.aut.android.deliveryapp.repository

import androidx.lifecycle.MutableLiveData
import hu.bme.aut.android.deliveryapp.api.DeliveryApi
import hu.bme.aut.android.deliveryapp.view.states.*

class ApiRepository {
    fun getUserHistory(id: String): MutableLiveData<JobDetailState> {
        return DeliveryApi.getUserHistory(id)
    }

    fun getAvailableJobs(sourceCity: String?, destinationCity: String?, price: Int?, date: String?): MutableLiveData<JobDetailState> {
        return DeliveryApi.getAvailableJobs(sourceCity, destinationCity, price, date)
    }

    fun getDeliveries (id: String): MutableLiveData<DeliveryState> {
        return DeliveryApi.getDeliveryData(id)
    }

    fun getJobsInProgress(id: String): MutableLiveData<JobDetailState> {
        return DeliveryApi.getJobsInProgress(id)
    }

    fun getUserData(id: String): MutableLiveData<UserState> {
        return DeliveryApi.getUserData(id)
    }

    fun getVehicleData(id: String): MutableLiveData<VehicleState> {
        return DeliveryApi.getVehicleData(id)
    }
/*
    fun getUserRating(id: String): MutableLiveData<UserWithRatingState> {
        return DeliveryApi.getUserRating(id)
    }
*/
    fun loginUser(token: String): MutableLiveData<UserState> {
        return DeliveryApi.loginUser(token)
    }
}
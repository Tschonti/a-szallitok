package hu.bme.aut.android.deliveryapp.repository

import androidx.lifecycle.MutableLiveData
import hu.bme.aut.android.deliveryapp.api.DeliveryApi
import hu.bme.aut.android.deliveryapp.model.Delivery
import hu.bme.aut.android.deliveryapp.model.DeliveryStatus
import hu.bme.aut.android.deliveryapp.view.states.*

class ApiRepository {
    fun getUserHistory(transporterId: String): MutableLiveData<DeliveryListState> {
        return DeliveryApi.getDeliveries(transporterId)
    }

    fun getAvailableJobs(): MutableLiveData<DeliveryListState> {
        return DeliveryApi.getDeliveries(null)
    }

    fun getDelivery (id: String): MutableLiveData<DeliveryState> {
        return DeliveryApi.getDeliveryData(id)
    }

    fun getJobsInProgress(transporterId: String): MutableLiveData<DeliveryListState> {
        return DeliveryApi.getDeliveries(transporterId)
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

    fun requestJob(id: String): MutableLiveData<DeliveryState> {
        return DeliveryApi.requestJob(id)
    }

    fun markJobAsReady(delivery: Delivery): MutableLiveData<DeliveryState> {
        return DeliveryApi.changeDeliveryStatus(delivery, DeliveryStatus.DELIVERED)
    }

    fun markDeliveryAsCancelled(delivery: Delivery): MutableLiveData<DeliveryState> {
        return DeliveryApi.changeDeliveryStatus(delivery, DeliveryStatus.UNASSIGNED)
    }

    fun rateClient(clientId: String, rating: Int): MutableLiveData<DeliveryState> {
        return DeliveryApi.rateClient(clientId, rating)
    }
}
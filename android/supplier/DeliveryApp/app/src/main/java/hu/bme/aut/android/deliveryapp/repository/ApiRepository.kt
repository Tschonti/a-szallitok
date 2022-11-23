package hu.bme.aut.android.deliveryapp.repository

import androidx.lifecycle.MutableLiveData
import hu.bme.aut.android.deliveryapp.api.DeliveryApi
import hu.bme.aut.android.deliveryapp.model.Delivery
import hu.bme.aut.android.deliveryapp.model.DeliveryStatus
import hu.bme.aut.android.deliveryapp.model.LocationUpdate
import hu.bme.aut.android.deliveryapp.model.Vehicle
import hu.bme.aut.android.deliveryapp.view.states.*

class ApiRepository {
    fun getUserHistory(): MutableLiveData<DeliveryInProgressState> {
        return DeliveryApi.getDeliveriesEarlier()
    }

    fun getAvailableJobs(): MutableLiveData<DeliveryListState> {
        return DeliveryApi.getDeliveries(null)
    }

    fun getDelivery (id: String): MutableLiveData<DeliveryState> {
        return DeliveryApi.getDeliveryData(id)
    }

    fun getUserData(id: String): MutableLiveData<UserState> {
        return DeliveryApi.getUserData(id)
    }

    fun getVehicleData(id: String): MutableLiveData<VehicleState> {
        return DeliveryApi.getVehicleData(id)
    }

    fun loginUser(token: String): MutableLiveData<UserState> {
        return DeliveryApi.loginUser(token)
    }

    fun requestJob(id: String): MutableLiveData<DeliveryState> {
        return DeliveryApi.requestJob(id)
    }

    fun markJobAsReady(delivery: Delivery): MutableLiveData<DeliveryState> {
        return DeliveryApi.changeDeliveryStatus(delivery, DeliveryStatus.DELIVERED)
    }

    fun markDeliveryAsInTransit(delivery: Delivery): MutableLiveData<DeliveryState> {
        return DeliveryApi.changeDeliveryStatus(delivery, DeliveryStatus.IN_TRANSIT)
    }

    fun rateClient(deliveryId: String, rating: Int): MutableLiveData<DeliveryState> {
        return DeliveryApi.rateClient(deliveryId, rating)
    }

    fun getDeliveriesInProgress(): MutableLiveData<DeliveryInProgressState> {
        return DeliveryApi.getDeliveriesInProgress()
    }

    fun addVehicle(vehicle: Vehicle): MutableLiveData<VehicleState> {
        return DeliveryApi.addVehicle(vehicle)
    }

    fun updateVehicle(vehicleId: String, vehicle: Vehicle): MutableLiveData<VehicleState> {
        return DeliveryApi.updateVehicle(vehicleId, vehicle)
    }

    fun updateLocation(deliveryId: String, location: LocationUpdate): MutableLiveData<DeliveryState> {
        return DeliveryApi.updateLocation(deliveryId, location)
    }
}
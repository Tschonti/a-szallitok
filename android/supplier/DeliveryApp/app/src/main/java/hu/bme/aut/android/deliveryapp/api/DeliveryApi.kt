package hu.bme.aut.android.deliveryapp.api

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import hu.bme.aut.android.deliveryapp.model.*
import hu.bme.aut.android.deliveryapp.repository.CurrentUser
import hu.bme.aut.android.deliveryapp.view.states.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object DeliveryApi {

    private val api = RetrofitClient.api

    fun getDeliveries(transporterId: String?): MutableLiveData<DeliveryListState> {
        val resultData = MutableLiveData<DeliveryListState>()
        resultData.value = DeliveryListState.inProgress


        api.getJobDetails(CurrentUser.token, transporterId).enqueue(object : Callback<List<Delivery>> {
            override fun onResponse(
                call: Call<List<Delivery>>,
                response: Response<List<Delivery>>
            ) {
                if (response.isSuccessful) {
                    resultData.postValue(response.body()
                        ?.let { DeliveryListState.deliveriesResponseSuccess(it) })
                } else {
                    resultData.postValue(DeliveryListState.deliveriesResponseError(response.message()))
                }
            }

            override fun onFailure(call: Call<List<Delivery>?>, throwable: Throwable) {
                Log.i("Error", throwable.message.toString())
                resultData.postValue(DeliveryListState.deliveriesResponseError(throwable.message.toString()))
            }
        })

        return resultData
    }

    fun getDeliveryData(id: String): MutableLiveData<DeliveryState> {

        val resultData = MutableLiveData<DeliveryState>()
        resultData.value = DeliveryState.inProgress

        api.getDeliveryData(CurrentUser.token, id).enqueue(object : Callback<Delivery> {
            override fun onResponse(
                call: Call<Delivery>,
                response: Response<Delivery>
            ) {
                if (response.isSuccessful) {
                    resultData.postValue(response.body()
                        ?.let { DeliveryState.deliveriesResponseSuccess(it) })
                } else {
                    resultData.postValue(DeliveryState.deliveriesResponseError(response.message()))
                }
            }

            override fun onFailure(call: Call<Delivery>, throwable: Throwable) {
                resultData.postValue(DeliveryState.deliveriesResponseError(throwable.message.toString()))
            }
        })

        return resultData
    }

    fun getUserData(id: String): MutableLiveData<UserState> {
        val resultData = MutableLiveData<UserState>()
        resultData.value = UserState.inProgress

            api.getUserData(CurrentUser.token, id).enqueue(object : Callback<User> {
                override fun onResponse(
                    call: Call<User>,
                    response: Response<User>
                ) {
                    if (response.isSuccessful) {
                        resultData.postValue(response.body()
                            ?.let { UserState.userResponseSuccess(it) })
                    } else {
                        Log.d("API_ERROR", response.message())
                        Log.d("TOKEN", CurrentUser.token)
                        resultData.postValue(UserState.userResponseError(response.message()))
                    }
                }

                override fun onFailure(call: Call<User>, throwable: Throwable) {
                    Log.d("API_ERROR", throwable.message.toString())
                    resultData.postValue(UserState.userResponseError(throwable.message.toString()))
                }
            })


        return resultData
    }

    fun getVehicleData(id: String): MutableLiveData<VehicleState> {
        val resultData = MutableLiveData<VehicleState>()
        resultData.value = VehicleState.inProgress

        api.getVehicleData(CurrentUser.token, id).enqueue(object : Callback<Vehicle> {
            override fun onResponse(
                call: Call<Vehicle>,
                response: Response<Vehicle>
            ) {
                if (response.isSuccessful) {
                    resultData.postValue(response.body()
                        ?.let { VehicleState.vehicleResponseSuccess(it) })
                } else {
                    resultData.postValue(VehicleState.vehicleResponseError(response.message()))
                }
            }

            override fun onFailure(call: Call<Vehicle>, throwable: Throwable) {
                resultData.postValue(VehicleState.vehicleResponseError(throwable.message.toString()))
            }
        })

        return resultData
    }

    fun loginUser(token: String): MutableLiveData<UserState> {
        val resultData = MutableLiveData<UserState>()
        resultData.value = UserState.inProgress

        api.loginUser("Bearer $token").enqueue(object : Callback<User> {
            override fun onResponse(
                call: Call<User>,
                response: Response<User>
            ) {
                if (response.isSuccessful) {
                    resultData.postValue(response.body()
                        ?.let { UserState.userResponseSuccess(it) })
                } else {
                    Log.d("ERROR", "e: " + response.message())
                    resultData.postValue(UserState.userResponseError(response.message()))
                }
            }

            override fun onFailure(call: Call<User>, throwable: Throwable) {
                Log.d("ERROR", "e: " + throwable.message.toString())
                resultData.postValue(UserState.userResponseError(throwable.message.toString()))
            }
        })

        return resultData
    }

    fun requestJob(id: String): MutableLiveData<DeliveryState> {
        val resultData = MutableLiveData<DeliveryState>()
        resultData.value = DeliveryState.inProgress

        api.requestJob(CurrentUser.token, id).enqueue(object : Callback<Delivery> {
            override fun onResponse(
                call: Call<Delivery>,
                response: Response<Delivery>
            ) {
                if (response.isSuccessful) {
                    resultData.postValue(response.body()
                        ?.let { DeliveryState.deliveriesResponseSuccess(it) })
                } else {
                    Log.d("ERROR", "e: " + response.message())
                    resultData.postValue(DeliveryState.deliveriesResponseError(response.message()))
                }
            }

            override fun onFailure(call: Call<Delivery>, throwable: Throwable) {
                Log.d("ERROR", "e: " + throwable.message.toString())
                resultData.postValue(DeliveryState.deliveriesResponseError(throwable.message.toString()))
            }
        })

        return resultData
    }

    fun rateClient(deliveryId: String, rating: Int): MutableLiveData<DeliveryState> {
        val resultData = MutableLiveData<DeliveryState>()
        resultData.value = DeliveryState.inProgress

        val r = RateChange(rating)
        api.rateClient(CurrentUser.token, deliveryId, r).enqueue(object : Callback<Delivery> {
            override fun onResponse(
                call: Call<Delivery>,
                response: Response<Delivery>
            ) {
                if (response.isSuccessful) {
                    resultData.postValue(response.body()
                        ?.let { DeliveryState.deliveriesResponseSuccess(it) })
                } else {
                    Log.d("ERROR", "e: " + response.message())
                    resultData.postValue(DeliveryState.deliveriesResponseError(response.message()))
                }
            }

            override fun onFailure(call: Call<Delivery>, throwable: Throwable) {
                Log.d("ERROR", "e: " + throwable.message.toString())
                resultData.postValue(DeliveryState.deliveriesResponseError(throwable.message.toString()))
            }
        })

        return resultData
    }

    fun changeDeliveryStatus(delivery: Delivery, newStatus: DeliveryStatus): MutableLiveData<DeliveryState> {
        val resultData = MutableLiveData<DeliveryState>()
        resultData.value = DeliveryState.inProgress

        val status = StatusChange(newStatus.toString())
        api.changeStatus(CurrentUser.token, delivery._id, status).enqueue(object : Callback<Delivery> {
            override fun onResponse(
                call: Call<Delivery>,
                response: Response<Delivery>
            ) {
                if (response.isSuccessful) {
                    resultData.postValue(response.body()
                        ?.let { DeliveryState.deliveriesResponseSuccess(it) })
                } else {
                    Log.d("ERROR", "e: " + response.message())
                    resultData.postValue(DeliveryState.deliveriesResponseError(response.message()))
                }
            }

            override fun onFailure(call: Call<Delivery>, throwable: Throwable) {
                Log.d("ERROR", "e: " + throwable.message.toString())
                resultData.postValue(DeliveryState.deliveriesResponseError(throwable.message.toString()))
            }
        })

        return resultData
    }

    fun getDeliveriesInProgress(): MutableLiveData<DeliveryInProgressState> {
        val resultData = MutableLiveData<DeliveryInProgressState>()
        resultData.value = DeliveryInProgressState.inProgress

        api.getJobsInProgress(CurrentUser.token).enqueue(object : Callback<List<DeliveryInProgress>> {
            override fun onResponse(
                call: Call<List<DeliveryInProgress>>,
                response: Response<List<DeliveryInProgress>>
            ) {
                if (response.isSuccessful) {
                    resultData.postValue(response.body()
                        ?.let { DeliveryInProgressState.deliveriesResponseSuccess(it.filter { dip -> dip.delivery.status == DeliveryStatus.PENDING || dip.delivery.status == DeliveryStatus.ASSIGNED || dip.delivery.status == DeliveryStatus.IN_TRANSIT}) })
                } else {
                    Log.d("ERROR", "e: " + response.message())
                    resultData.postValue(DeliveryInProgressState.deliveriesResponseError(response.message()))
                }
            }

            override fun onFailure(call: Call<List<DeliveryInProgress>>, throwable: Throwable) {
                Log.d("ERROR", "e: " + throwable.message.toString())
                resultData.postValue(DeliveryInProgressState.deliveriesResponseError(throwable.message.toString()))
            }
        })

        return resultData
    }

    fun getDeliveriesEarlier(): MutableLiveData<DeliveryInProgressState> {
        val resultData = MutableLiveData<DeliveryInProgressState>()
        resultData.value = DeliveryInProgressState.inProgress

        api.getJobsInProgress(CurrentUser.token).enqueue(object : Callback<List<DeliveryInProgress>> {
            override fun onResponse(
                call: Call<List<DeliveryInProgress>>,
                response: Response<List<DeliveryInProgress>>
            ) {
                if (response.isSuccessful) {
                    resultData.postValue(response.body()
                        ?.let { DeliveryInProgressState.deliveriesResponseSuccess(it.filter { dip -> dip.delivery.status == DeliveryStatus.DELIVERED }) })
                } else {
                    Log.d("ERROR", "e: " + response.message())
                    resultData.postValue(DeliveryInProgressState.deliveriesResponseError(response.message()))
                }
            }

            override fun onFailure(call: Call<List<DeliveryInProgress>>, throwable: Throwable) {
                Log.d("ERROR", "e: " + throwable.message.toString())
                resultData.postValue(DeliveryInProgressState.deliveriesResponseError(throwable.message.toString()))
            }
        })

        return resultData
    }

    fun addVehicle(vehicle: Vehicle): MutableLiveData<VehicleState> {
        val resultData = MutableLiveData<VehicleState>()
        resultData.value = VehicleState.inProgress

        api.addVehicle(CurrentUser.token, vehicle).enqueue(object : Callback<Vehicle> {
            override fun onResponse(
                call: Call<Vehicle>,
                response: Response<Vehicle>
            ) {
                if (response.isSuccessful) {
                    resultData.postValue(response.body()
                        ?.let { VehicleState.vehicleResponseSuccess(it)
                    })
                } else {
                    Log.d("ERROR", "e: " + response.message())
                    resultData.postValue(VehicleState.vehicleResponseError(response.message()))
                }
            }

            override fun onFailure(call: Call<Vehicle>, throwable: Throwable) {
                Log.d("ERROR", "e: " + throwable.message.toString())
                resultData.postValue(VehicleState.vehicleResponseError(throwable.message.toString()))
            }
        })

        return resultData
    }

    fun updateVehicle(vehicleId: String, vehicle: Vehicle): MutableLiveData<VehicleState> {
        val resultData = MutableLiveData<VehicleState>()
        resultData.value = VehicleState.inProgress

        api.updateVehicle(CurrentUser.token, vehicleId, vehicle).enqueue(object : Callback<Vehicle> {
            override fun onResponse(
                call: Call<Vehicle>,
                response: Response<Vehicle>
            ) {
                if (response.isSuccessful) {
                    resultData.postValue(response.body()
                        ?.let { VehicleState.vehicleResponseSuccess(it)
                        })
                } else {
                    Log.d("ERROR", "e: " + response.message())
                    resultData.postValue(VehicleState.vehicleResponseError(response.message()))
                }
            }

            override fun onFailure(call: Call<Vehicle>, throwable: Throwable) {
                Log.d("ERROR", "e: " + throwable.message.toString())
                resultData.postValue(VehicleState.vehicleResponseError(throwable.message.toString()))
            }
        })

        return resultData
    }

    fun updateLocation(deliveryId: String, location: LocationUpdate): MutableLiveData<DeliveryState> {
        val resultData = MutableLiveData<DeliveryState>()
        resultData.value = DeliveryState.inProgress

        api.updateLocation(CurrentUser.token, deliveryId, location).enqueue(object : Callback<Delivery> {
            override fun onResponse(
                call: Call<Delivery>,
                response: Response<Delivery>
            ) {
                if (response.isSuccessful) {
                    resultData.postValue(response.body()
                        ?.let { DeliveryState.deliveriesResponseSuccess(it)
                        })
                } else {
                    Log.d("ERROR", "e: " + response.message())
                    resultData.postValue(DeliveryState.deliveriesResponseError(response.message()))
                }
            }

            override fun onFailure(call: Call<Delivery>, throwable: Throwable) {
                Log.d("ERROR", "e: " + throwable.message.toString())
                resultData.postValue(DeliveryState.deliveriesResponseError(throwable.message.toString()))
            }
        })

        return resultData
    }
}
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

    fun getUserHistory(id: String): MutableLiveData<DeliveryListState> {

        val resultData = MutableLiveData<DeliveryListState>()
        resultData.value = DeliveryListState.inProgress

        api.getUserHistory(CurrentUser.token, id).enqueue(object : Callback<List<Delivery>> {
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
                resultData.postValue(DeliveryListState.deliveriesResponseError(throwable.message.toString()))
            }
        })

        return resultData
    }

    fun getAvailableJobs(sourceCity: String?, destinationCity: String?, price: Int?, date: String?): MutableLiveData<DeliveryListState> {
        val resultData = MutableLiveData<DeliveryListState>()
        resultData.value = DeliveryListState.inProgress

        api.getJobDetails(CurrentUser.token, "OPEN", sourceCity, destinationCity, price, null, date).enqueue(object : Callback<List<Delivery>> {
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

    fun getJobsInProgress(id: String): MutableLiveData<DeliveryListState> {
        val resultData = MutableLiveData<DeliveryListState>()
        resultData.value = DeliveryListState.inProgress


        api.getJobDetails(CurrentUser.token, "INPROGRESS",  null, null, null, id, null).enqueue(object : Callback<List<Delivery>> {
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

        FirebaseAuth.getInstance().currentUser?.getIdToken(false)?.addOnSuccessListener {
            api.getUserData(it.token!!, id).enqueue(object : Callback<User> {
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
        }


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
/*
    fun getUserRating(id: String): MutableLiveData<UserWithRatingState> {
        val resultData = MutableLiveData<UserWithRatingState>()
        resultData.value = UserWithRatingState.inProgress

        api.getRating(CurrentUser.token, id).enqueue(object : Callback<UserWithRating> {
            override fun onResponse(
                call: Call<UserWithRating>,
                response: Response<UserWithRating>
            ) {
                if (response.isSuccessful) {
                    resultData.postValue(response.body()
                        ?.let { UserWithRatingState.userResponseSuccess(it) })
                } else {
                    resultData.postValue(UserWithRatingState.userResponseError(response.message()))
                }
            }

            override fun onFailure(call: Call<UserWithRating>, throwable: Throwable) {
                resultData.postValue(UserWithRatingState.userResponseError("ERROR"))
            }
        })

        return resultData
    }
*/
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

        api.requestJob(CurrentUser.token, id, CurrentUser.user._id).enqueue(object : Callback<Delivery> {
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

    fun rateClient(clientId: String, rating: Int): MutableLiveData<DeliveryState> {
        val resultData = MutableLiveData<DeliveryState>()
        resultData.value = DeliveryState.inProgress

        api.rateClient(CurrentUser.token, clientId, rating).enqueue(object : Callback<Delivery> {
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

    fun markDeliveryAsReady(delivery: Delivery): MutableLiveData<DeliveryState> {
        val resultData = MutableLiveData<DeliveryState>()
        resultData.value = DeliveryState.inProgress

        delivery.status = "DONE"
        api.changeStatus(CurrentUser.token, delivery._id, delivery).enqueue(object : Callback<Delivery> {
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

    fun markDeliveryAsCancelled(delivery: Delivery): MutableLiveData<DeliveryState> {
        val resultData = MutableLiveData<DeliveryState>()
        resultData.value = DeliveryState.inProgress

        delivery.status = "CANCEL"
        api.changeStatus(CurrentUser.token, delivery._id, delivery).enqueue(object : Callback<Delivery> {
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
}
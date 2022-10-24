package hu.bme.aut.android.deliveryapp.api

import android.util.Log
import androidx.lifecycle.MutableLiveData
import hu.bme.aut.android.deliveryapp.model.*
import hu.bme.aut.android.deliveryapp.view.states.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object DeliveryApi {

    private val api = RetrofitClient.api

    fun getUserHistory(id: Int): MutableLiveData<JobDetailState> {

        val resultData = MutableLiveData<JobDetailState>()
        resultData.value = JobDetailState.inProgress

        api.getUserHistory(id).enqueue(object : Callback<List<JobDetails>> {
            override fun onResponse(
                call: Call<List<JobDetails>>,
                response: Response<List<JobDetails>>
            ) {
                if (response.isSuccessful) {
                    resultData.postValue(response.body()
                        ?.let { JobDetailState.jobDetailsResponseSuccess(it) })
                } else {
                    resultData.postValue(JobDetailState.jobDetailsResponseError(response.message()))
                }
            }

            override fun onFailure(call: Call<List<JobDetails>?>, throwable: Throwable) {
                resultData.postValue(JobDetailState.jobDetailsResponseError("ERROR"))
            }
        })

        return resultData
    }

    fun getAvailableJobs(sourceCity: String?, destinationCity: String?, price: Int?, date: String?): MutableLiveData<JobDetailState> {
        val resultData = MutableLiveData<JobDetailState>()
        resultData.value = JobDetailState.inProgress

        api.getJobDetails("OPEN", sourceCity, destinationCity, price, null, date).enqueue(object : Callback<List<JobDetails>> {
            override fun onResponse(
                call: Call<List<JobDetails>>,
                response: Response<List<JobDetails>>
            ) {
                if (response.isSuccessful) {
                    resultData.postValue(response.body()
                        ?.let { JobDetailState.jobDetailsResponseSuccess(it) })
                } else {
                    resultData.postValue(JobDetailState.jobDetailsResponseError(response.message()))
                }
            }

            override fun onFailure(call: Call<List<JobDetails>?>, throwable: Throwable) {
                Log.i("Error", throwable.message.toString())
                resultData.postValue(JobDetailState.jobDetailsResponseError("ERROR"))
            }
        })

        return resultData
    }

    fun getJobsInProgress(id: Int): MutableLiveData<JobDetailState> {
        val resultData = MutableLiveData<JobDetailState>()
        resultData.value = JobDetailState.inProgress

        api.getJobDetails("INPROGRESS",  null, null, null, id, null).enqueue(object : Callback<List<JobDetails>> {
            override fun onResponse(
                call: Call<List<JobDetails>>,
                response: Response<List<JobDetails>>
            ) {
                if (response.isSuccessful) {
                    resultData.postValue(response.body()
                        ?.let { JobDetailState.jobDetailsResponseSuccess(it) })
                } else {
                    resultData.postValue(JobDetailState.jobDetailsResponseError(response.message()))
                }
            }

            override fun onFailure(call: Call<List<JobDetails>?>, throwable: Throwable) {
                Log.i("Error", throwable.message.toString())
                resultData.postValue(JobDetailState.jobDetailsResponseError("ERROR"))
            }
        })

        return resultData
    }

    fun getDeliveryData(id: Int): MutableLiveData<DeliveryState> {

        val resultData = MutableLiveData<DeliveryState>()
        resultData.value = DeliveryState.inProgress

        api.getDeliveryData(id).enqueue(object : Callback<Delivery> {
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
                resultData.postValue(DeliveryState.deliveriesResponseError("ERROR"))
            }
        })

        return resultData
    }

    fun getUserData(id: Int): MutableLiveData<UserState> {
        val resultData = MutableLiveData<UserState>()
        resultData.value = UserState.inProgress

        api.getUserData(id).enqueue(object : Callback<User> {
            override fun onResponse(
                call: Call<User>,
                response: Response<User>
            ) {
                if (response.isSuccessful) {
                    resultData.postValue(response.body()
                        ?.let { UserState.userResponseSuccess(it) })
                } else {
                    resultData.postValue(UserState.userResponseError(response.message()))
                }
            }

            override fun onFailure(call: Call<User>, throwable: Throwable) {
                resultData.postValue(UserState.userResponseError("ERROR"))
            }
        })

        return resultData
    }

    fun getVehicleData(id: Int): MutableLiveData<VehicleState> {
        val resultData = MutableLiveData<VehicleState>()
        resultData.value = VehicleState.inProgress

        api.getVehicleData(id).enqueue(object : Callback<Vehicle> {
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
                resultData.postValue(VehicleState.vehicleResponseError("ERROR"))
            }
        })

        return resultData
    }

    fun getUserRating(id: Int): MutableLiveData<UserWithRatingState> {
        val resultData = MutableLiveData<UserWithRatingState>()
        resultData.value = UserWithRatingState.inProgress

        api.getRating(id).enqueue(object : Callback<UserWithRating> {
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
                resultData.postValue(UserState.userResponseError("ERROR"))
            }
        })

        return resultData
    }
}
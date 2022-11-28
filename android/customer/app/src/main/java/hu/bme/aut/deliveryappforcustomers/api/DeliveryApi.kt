package hu.bme.aut.deliveryappforcustomers.api

import android.util.Log
import androidx.lifecycle.MutableLiveData
import hu.bme.aut.android.deliveryapp.model.Delivery
import hu.bme.aut.android.deliveryapp.model.User
import hu.bme.aut.deliveryappforcustomers.model.DeliveryStatus
import hu.bme.aut.deliveryappforcustomers.model.DeliveryWithUserAndStatus
import hu.bme.aut.deliveryappforcustomers.model.Reply
import hu.bme.aut.deliveryappforcustomers.repository.CurrentUser
import hu.bme.aut.deliveryappforcustomers.view.JobDetailState
import hu.bme.aut.deliveryappforcustomers.view.states.UserState
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log


object DeliveryApi {

    private val api = RetrofitClient.api


    fun createNewTransport(delivery: Delivery): MutableLiveData<Delivery> {
        val resultData = MutableLiveData<Delivery>()
        delivery.status = DeliveryStatus.DELIVERED
        api.createNewTransport(CurrentUser.token, delivery).enqueue(object : Callback<Delivery> {
            override fun onResponse(
                call: Call<Delivery>,
                response: Response<Delivery>
            ) {
                if (response.isSuccessful) {
                    resultData.postValue(response.body())
                } else {
                    Log.d("ERROR", "unsuccessful response, e: " + response.message())
                    resultData.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<Delivery>, throwable: Throwable) {
                Log.d("ERROR", "onfailure method, e: " + throwable.message.toString())
            }
        })

        return resultData
    }

    fun getJobRequests(): MutableLiveData<List<DeliveryWithUserAndStatus>?> {
        val resultData = MutableLiveData<List<DeliveryWithUserAndStatus>?>()

        GlobalScope.async {
            val response = RetrofitClient.api.getJobRequests(CurrentUser.token)
            resultData.postValue(response.body())
        }.start()

        return resultData
    }

    fun reply(deliveryID: String, reply: Reply): MutableLiveData<Delivery> {
        val resultData = MutableLiveData<Delivery>()
        GlobalScope.async {
            val response = api.reply(CurrentUser.token, deliveryID, reply)
            Log.d("RESPONSE", response.message().toString())
        }.start()

        return resultData
    }

    fun getUserHistory(id: Int): MutableLiveData<JobDetailState> {

        val resultData = MutableLiveData<JobDetailState>()
        resultData.value = JobDetailState.inProgress

        GlobalScope.launch {
            val response = RetrofitClient.api.getUserHistory(id)
            val result = response.body()
            if (response.isSuccessful && result != null) {
                resultData.postValue(JobDetailState.jobDetailsResponseSuccess(result))
            } else {
                resultData.postValue(
                    JobDetailState.jobDetailsResponseError(
                        response.errorBody().toString()
                    )
                )
            }
        }.start()

        return resultData
    }

    fun getJobsInProgress(id: Int): MutableLiveData<JobDetailState> {

        val resultData = MutableLiveData<JobDetailState>()
        resultData.value = JobDetailState.inProgress

        GlobalScope.launch {
            val response = RetrofitClient.api.getDeliveries(
                null, null, null, null, null, null
            )
            val result = response.body()
            if (response.isSuccessful && result != null) {
                resultData.postValue(JobDetailState.jobDetailsResponseSuccess(result))
            } else {
                resultData.postValue(
                    JobDetailState.jobDetailsResponseError(
                        response.errorBody().toString()
                    )
                )
            }
        }.start()

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
}
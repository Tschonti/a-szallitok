package hu.bme.aut.deliveryappforcustomers.api

import android.util.Log
import androidx.lifecycle.MutableLiveData
import hu.bme.aut.android.deliveryapp.model.Delivery
import hu.bme.aut.deliveryappforcustomers.api.RetrofitClient.api
import hu.bme.aut.deliveryappforcustomers.model.DeliveryStatus
import hu.bme.aut.deliveryappforcustomers.repository.CurrentUser
import hu.bme.aut.deliveryappforcustomers.view.JobDetailState
import hu.bme.aut.deliveryappforcustomers.view.states.DeliveryState
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object DeliveryApi {

    private val api = RetrofitClient.api

  

    fun createNewTransport(delivery: Delivery) : MutableLiveData<DeliveryState> {
        val resultData = MutableLiveData<DeliveryState>()
        resultData.value = DeliveryState.inProgress

        delivery.status = DeliveryStatus.DELIVERED
        api.createNewTransport(CurrentUser.token, delivery).enqueue(object : Callback<Delivery> {
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

    fun getActiveTransports(id: Int): MutableLiveData<JobDetailState> {

        val resultData = MutableLiveData<JobDetailState>()
        resultData.value = JobDetailState.inProgress

        GlobalScope.launch {
            val response = RetrofitClient.api.getUserHistory(id)
            val result = response.body()?.any{
                true //TODO tbd
            }
            if (response.isSuccessful && result != null) {
                //resultData.postValue(JobDetailState.jobDetailsResponseSuccess(result!!)) //TODO tbd
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
}
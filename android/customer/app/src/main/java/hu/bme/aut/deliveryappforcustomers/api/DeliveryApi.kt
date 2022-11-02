package hu.bme.aut.deliveryappforcustomers.api

import androidx.lifecycle.MutableLiveData
import hu.bme.aut.deliveryappforcustomers.view.JobDetailState
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


object DeliveryApi {

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
                resultData.postValue(JobDetailState.jobDetailsResponseSuccess(result!!))
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
                resultData.postValue(JobDetailState.jobDetailsResponseSuccess(result!!))
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
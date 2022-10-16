package hu.bme.aut.android.deliveryapp.api

import androidx.lifecycle.MutableLiveData
import hu.bme.aut.android.deliveryapp.model.Delivery
import hu.bme.aut.android.deliveryapp.model.JobDetails
import hu.bme.aut.android.deliveryapp.model.User
import hu.bme.aut.android.deliveryapp.view.DeliveryState
import hu.bme.aut.android.deliveryapp.view.JobDetailState
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object DeliveryApi {

    fun getUserHistory(id: Int): MutableLiveData<JobDetailState> {

        val resultData = MutableLiveData<JobDetailState>()
        resultData.value = JobDetailState.inProgress

        GlobalScope.launch {
            val response = RetrofitClient.api.getUserHistory(id)
            val result = response.body()
            if (response.isSuccessful && result != null) {
                resultData.postValue(JobDetailState.jobDetailsResponseSuccess(result!!))
            }
            else {
                resultData.postValue(JobDetailState.jobDetailsResponseError(response.errorBody().toString()))
            }
        }.start()

        return resultData
    }

    fun getAvailableJobs(id: Int): MutableLiveData<JobDetailState> {

        val resultData = MutableLiveData<JobDetailState>()
        resultData.value = JobDetailState.inProgress

        GlobalScope.launch {
            val response = RetrofitClient.api.getDeliveries(
                null, null, null, null, null, null)
            val result = response.body()
            if (response.isSuccessful && result != null) {
                resultData.postValue(JobDetailState.jobDetailsResponseSuccess(result!!))
            }
            else {
                resultData.postValue(JobDetailState.jobDetailsResponseError(response.errorBody().toString()))
            }
        }.start()

        return resultData
    }

    fun getJobsInProgress(id: Int): MutableLiveData<JobDetailState> {

        val resultData = MutableLiveData<JobDetailState>()
        resultData.value = JobDetailState.inProgress

        GlobalScope.launch {
            val response = RetrofitClient.api.getDeliveries(
                null, null, null, null, null, null)
            val result = response.body()
            if (response.isSuccessful && result != null) {
                resultData.postValue(JobDetailState.jobDetailsResponseSuccess(result!!))
            }
            else {
                resultData.postValue(JobDetailState.jobDetailsResponseError(response.errorBody().toString()))
            }
        }.start()

        return resultData
    }

    fun getDeliveryData(id: Int): MutableLiveData<DeliveryState> {
        val resultData = MutableLiveData<DeliveryState>()
        resultData.value = DeliveryState.inProgress

        GlobalScope.launch {
            val response = RetrofitClient.api.getDeliveryData(id)
            val result = response.body()
            if (response.isSuccessful && result != null) {
                resultData.postValue(DeliveryState.deliveriesResponseSuccess(result!!))
            }
            else {
                resultData.postValue(DeliveryState.deliveriesResponseError(response.errorBody().toString()))
            }
        }.start()

        return resultData
    }
}
package hu.bme.aut.deliveryappforcustomers.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hu.bme.aut.deliveryappforcustomers.model.DeliveryWithUserAndStatus
import hu.bme.aut.deliveryappforcustomers.model.Reply
import hu.bme.aut.deliveryappforcustomers.repository.ApiRepository

class ActiveTransportsViewModel : ViewModel() {

    private val apiRepository = ApiRepository()

    fun getJobRequests(): MutableLiveData<List<DeliveryWithUserAndStatus>?> {
        val resultData = apiRepository.getJobRequests()
        Log.d("DATA", resultData.value.toString())
        return resultData
    }

    fun reply(deliveryId: String, reply: Reply) {
        apiRepository.reply(deliveryId, reply)
    }
}
package hu.bme.aut.deliveryappforcustomers.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val baseUrl = "https://a-szallitok-api.azurewebsites.net/"

    val api = getInstance().create(ApiService::class.java)

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

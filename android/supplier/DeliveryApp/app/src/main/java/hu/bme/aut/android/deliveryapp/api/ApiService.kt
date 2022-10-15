package hu.bme.aut.android.deliveryapp.api

import hu.bme.aut.android.deliveryapp.model.*
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path


interface ApiService {
    @GET("users/{ID}")
    fun getUserData(@Path("id") id: Int): Call<List<User?>?>?

    @GET("users/{ID}/history")
    fun getUserHistory(@Path("id") id: Int): Call<List<JobDetails?>?>?

    @GET("vehicle/{ID}")
    fun getVehicleData(@Path("id") id: Int): Call<List<Vehicle?>?>?

    @GET("delivery/{ID}")
    fun getDeliveryData(@Path("id") id: Int): Call<List<Delivery?>?>?

    @GET("delivery/{ID}/jobDetails")
    fun getJobDetails(@Path("id") id: Int): Call<List<JobDetails?>?>?
}
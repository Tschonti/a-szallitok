package hu.bme.aut.android.deliveryapp.api

import hu.bme.aut.android.deliveryapp.model.*
import hu.bme.aut.android.deliveryapp.view.states.DeliveryState
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


interface ApiService {
    @GET("/user/{ID}")
    @Headers("Cache-control: no-cache")
    fun getUserData(@Header("Authorization") token: String, @Path("ID") ID: String): Call<User>

    @GET("vehicle/{ID}")
    @Headers("Cache-control: no-cache")
    fun getVehicleData(@Header("Authorization") token: String, @Path("ID") ID: String): Call<Vehicle>

    @GET("delivery")
    @Headers("Cache-control: no-cache")
    fun getJobDetails(@Header("Authorization") token: String, @Query("transporterId") transporterId: String?): Call<List<Delivery>>

    @GET("user/requestedJobs")
    @Headers("Cache-control: no-cache")
    fun getJobsInProgress(@Header("Authorization") token: String): Call<List<DeliveryInProgress>>

    @GET("delivery/{ID}")
    @Headers("Cache-control: no-cache")
    fun getDeliveryData(@Header("Authorization") token: String, @Path("ID") ID: String): Call<Delivery>

    @GET("login")
    @Headers("Cache-control: no-cache")
    fun loginUser(@Header("Authorization") token: String): Call<User>

    @POST("delivery/{ID}/request")
    @Headers("Cache-control: no-cache")
    fun requestJob(@Header("Authorization") token: String, @Path("ID") ID: String): Call<Delivery>

    @POST("vehicle")
    @Headers("Cache-control: no-cache")
    fun addVehicle(@Header("Authorization") token: String, @Body vehicle: Vehicle): Call<Vehicle>

    @PUT("vehicle/{ID}")
    @Headers("Cache-control: no-cache")
    fun updateVehicle(@Header("Authorization") token: String, @Path("ID") ID: String, @Body vehicle: Vehicle): Call<Vehicle>

    @PUT("delivery/{ID}/rateClient")
    @Headers("Cache-control: no-cache")
    fun rateClient(@Header("Authorization") token: String, @Path("ID") ID: String, @Body rating: RateChange): Call<Delivery>

    @PUT("delivery/{ID}/statusChange")
    @Headers("Cache-control: no-cache")
    fun changeStatus(@Header("Authorization") token: String, @Path("ID") ID: String, @Body status: StatusChange): Call<Delivery>
}
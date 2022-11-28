package hu.bme.aut.deliveryappforcustomers.api

import hu.bme.aut.android.deliveryapp.model.Delivery
import hu.bme.aut.android.deliveryapp.model.JobDetails
import hu.bme.aut.android.deliveryapp.model.User
import hu.bme.aut.android.deliveryapp.model.Vehicle
import hu.bme.aut.deliveryappforcustomers.model.DeliveryWithUserAndStatus
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("/user/{ID}")
    fun getUserData(@Path("ID") ID: Int): Response<User>

    @GET("vehicle/{ID}")
    fun getVehicleData(@Path("ID") ID: Int): Response<List<Vehicle>>

    @GET("delivery/{ID}")
    fun getDeliveryData(@Path("ID") ID: Int): Response<List<Delivery>>

    @GET("delivery/{ID}/jobDetails")
    fun getJobDetails(@Path("ID") ID: Int): Response<List<JobDetails>>

    @GET("user/{ID}/history")
    fun getUserHistory(@Path("ID") ID: Int): Response<List<JobDetails>>

    @GET("delivery/")
    fun getDeliveries(
        @Query("status") status: String?,
        @Query("sourceCity") sourceCity: String?,
        @Query("destinationCity") destinationCity: String?,
        @Query("price") price: Int?,
        @Query("transporterId") transporterId: Int?,
        @Query("date") date: String?
    ): Response<List<JobDetails>>

    @POST("delivery/")
    fun createNewTransport(
        @Header("Authorization") token: String,
        @Body delivery: Delivery
    ): Call<Delivery>

    @GET("user/jobRequests")
    fun getJobRequests(@Header("Authorization") token: String): Call<List<DeliveryWithUserAndStatus>?>

    @GET("login")
    fun loginUser(@Header("Authorization") token: String): Call<User>
}
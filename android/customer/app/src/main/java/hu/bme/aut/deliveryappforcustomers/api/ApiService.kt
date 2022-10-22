package hu.bme.aut.deliveryappforcustomers.api

import hu.bme.aut.android.deliveryapp.model.Delivery
import hu.bme.aut.android.deliveryapp.model.JobDetails
import hu.bme.aut.android.deliveryapp.model.User
import hu.bme.aut.android.deliveryapp.model.Vehicle
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/user/{ID}")
    suspend fun getUserData(@Path("ID") ID: Int): Response<User>

    @GET("user/{ID}/history")
    suspend fun getUserHistory(@Path("ID") ID: Int): Response<List<JobDetails>>

    @GET("vehicle/{ID}")
    suspend fun getVehicleData(@Path("ID") ID: Int): Response<List<Vehicle>>

    @GET("delivery/")
    suspend fun getDeliveries(@Query("status") status: String?,
                              @Query("sourceCity") sourceCity: String?,
                              @Query("destinationCity") destinationCity: String?,
                              @Query("price") price: Int?,
                              @Query("transporterId") transporterId: Int?,
                              @Query("date") date: String?
    ): Response<List<JobDetails>>

    @GET("delivery/{ID}")
    suspend fun getDeliveryData(@Path("ID") ID: Int): Response<List<Delivery>>

    @GET("delivery/{ID}/jobDetails")
    suspend fun getJobDetails(@Path("ID") ID: Int): Response<List<JobDetails>>
}
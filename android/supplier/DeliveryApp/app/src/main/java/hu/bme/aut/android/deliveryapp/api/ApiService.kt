package hu.bme.aut.android.deliveryapp.api

import hu.bme.aut.android.deliveryapp.model.*
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


interface ApiService {
    @GET("/user/{ID}")
    fun getUserData(@Path("ID") ID: Int): Call<User>

    @GET("user/{ID}/history")
    fun getUserHistory(@Path("ID") ID: Int): Call<List<JobDetails>>

    @GET("vehicle/{ID}")
    fun getVehicleData(@Path("ID") ID: Int): Call<Vehicle>

    @GET("delivery/jobDetails")
    fun getJobDetails(@Query("status") status: String?,
                        @Query("sourceCity") sourceCity: String?,
                        @Query("destinationCity") destinationCity: String?,
                        @Query("price") price: Int?,
                        @Query("transporterId") transporterId: Int?,
                        @Query("date") date: String?
    ): Call<List<JobDetails>>

    @GET("delivery/{ID}/jobDetails")
    fun getJobDetail(@Path("ID") ID: Int?): Call<JobDetails>

    @GET("delivery/{ID}")
    fun getDeliveryData(@Path("ID") ID: Int): Call<Delivery>

    @GET("user/{ID}/rating")
    fun getRating(@Path("ID") ID: Int): Call<UserWithRating>

    @GET("user/login")
    fun loginUser(@Header("token") token: String): Call<User>
}
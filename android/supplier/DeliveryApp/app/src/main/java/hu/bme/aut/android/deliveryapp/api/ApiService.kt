package hu.bme.aut.android.deliveryapp.api

import hu.bme.aut.android.deliveryapp.model.*
import hu.bme.aut.android.deliveryapp.view.states.DeliveryState
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


interface ApiService {
    @GET("/user/{ID}")
    fun getUserData(@Header("Authorization") token: String, @Path("ID") ID: String): Call<User>

    @GET("user/{ID}/history")
    fun getUserHistory(@Header("Authorization") token: String, @Path("ID") ID: String): Call<List<Delivery>>

    @GET("vehicle/{ID}")
    fun getVehicleData(@Header("Authorization") token: String, @Path("ID") ID: String): Call<Vehicle>

    @GET("delivery")
    fun getJobDetails(@Header("Authorization") token: String,
                        @Query("transporterId") transporterId: String?,
    ): Call<List<Delivery>>

    @GET("delivery/{ID}")
    fun getDeliveryData(@Header("Authorization") token: String, @Path("ID") ID: String): Call<Delivery>

    @GET("login")
    fun loginUser(@Header("Authorization") token: String): Call<User>

    @PUT("delivery/{ID}/request")
    fun requestJob(@Header("Authorization") token: String, @Path("ID") ID: String, @Body transporterId: String): Call<Delivery>

    @PUT("delivery/{ID}/rateClient")
    fun rateClient(@Header("Authorization") token: String, @Path("ID") ID: String, @Body rating: Int): Call<Delivery>

    @PUT("delivery/{ID}")
    fun changeStatus(@Header("Authorization") token: String, @Path("ID") ID: String, @Body delivery: Delivery): Call<Delivery>
}
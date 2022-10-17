package hu.bme.aut.deliveryappforcustomers.api

object RetrofitClient {
    val baseUrl = "http://10.0.2.2:8000/"  

    val api = getInstance().create(ApiService::class.java)

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            // we need to add converter factory to
            // convert JSON object to Java object
            .build()
    }
}

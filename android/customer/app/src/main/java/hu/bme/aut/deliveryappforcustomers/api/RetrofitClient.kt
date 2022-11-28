package hu.bme.aut.deliveryappforcustomers.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val baseUrl = "https://a-szallitok-api.azurewebsites.net/"

    val api = getInstance().create(ApiService::class.java)


// add your other interceptors …
// add logging as last interceptor


    fun getInstance(): Retrofit {
        val logging = HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        val httpClient = OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
    }
}

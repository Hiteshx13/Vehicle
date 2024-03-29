package com.veh.demo.network

import com.veh.demo.util.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.GsonBuilder

import com.google.gson.Gson




class RetrofitInstance {

    companion object {

        private val retrofit by lazy {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            var client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            val gson = GsonBuilder()
                .setLenient()
                .create()
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()

        }
//        val retrofit=Retrofit
//            .Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()

        val api by lazy {
            retrofit.create(VehicleAPI::class.java)
        }
    }


//    suspend fun getOwnerList():List<OwnerResponse>{
//        return networkInterface.getOwnerList()
//    }
}
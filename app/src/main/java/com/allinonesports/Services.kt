package com.allinonesports

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface Services {

    companion object {
        private const val timeOut = 30

        private var okHttpClient = OkHttpClient.Builder().addInterceptor { chain ->
            val request = chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .build()
            chain.proceed(request)
        }.connectTimeout(timeOut.toLong(), TimeUnit.SECONDS).build()

        var retrofit: Retrofit = Retrofit.Builder().baseUrl("https://newsapi.org/v2/").addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()

        var service: Services = Services.retrofit.create(Services::class.java)
    }

    @GET("top-headlines?country=us&apiKey=d2d16f0f67534b63a39bf15dc1002dd2")
    fun getHeadings(): Call<HeadlineResponse>


    @GET("sources?category=sports&language=en&country=us&apiKey=d2d16f0f67534b63a39bf15dc1002dd2")
    fun getEvents(): Call<EventResponse>


}
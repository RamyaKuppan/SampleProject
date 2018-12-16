package com.allinonesports

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface ProductService {
    companion object {
        private const val timeOut = 30

        private var okHttpClient = OkHttpClient.Builder().addInterceptor { chain ->
            val request = chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .build()
            chain.proceed(request)
        }.connectTimeout(timeOut.toLong(), TimeUnit.SECONDS).build()

        var retrofit: Retrofit = Retrofit.Builder().baseUrl("https://api.predic8.de/").addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()

        var productService: ProductService = ProductService.retrofit.create(ProductService::class.java)
    }

    @GET("shop/products/")
    fun getProduct(): Call<ProductResponse>

}
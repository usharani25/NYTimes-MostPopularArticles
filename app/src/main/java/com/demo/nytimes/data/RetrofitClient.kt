package com.demo.nytimes.data

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import com.google.gson.GsonBuilder

object RetrofitClient {


const val  api_key="jM5K0PwkCBT7c5Tn5JWXdJk34u3JUOOo"
    private const val BASE_URL = "http://api.nytimes.com/svc/mostpopular/v2/mostviewed/all-sections/"

    private var gson = GsonBuilder()
        .create()

    private fun getRetrofitClient() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    fun getApiService(): ServiceApi = getRetrofitClient().create(ServiceApi::class.java)





}
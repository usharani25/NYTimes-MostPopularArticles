package com.demo.nytimes.data

import com.demo.nytimes.models.AllSectionModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApi {

    @GET("7.json?")
    fun getPopular(
        @Query(
            value = "api-key"
        ) apiKey: String
    ): Call<AllSectionModel>
}
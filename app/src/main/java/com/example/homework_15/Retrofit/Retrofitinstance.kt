package com.example.homework_15.Retrofit

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Retrofitinstance {
    private const val BASE_URL = "https://reqres.in/api/"

    suspend fun request(): ApiClient =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(ApiClient::class.java)
}
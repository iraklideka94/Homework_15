package com.example.homework_15.Retrofit

import com.example.homework_15.model.Login
import com.example.homework_15.model.LoginModel
import com.example.homework_15.model.Registration
import com.example.homework_15.model.RegistrationModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiClient {
    @POST("login")
    suspend fun getLoginData(@Body userData: LoginModel): Response<Login>

    @POST("register")
    suspend fun getRegData(@Body userData: RegistrationModel): Response<Registration>
}
package com.example.homework_15.Resource

sealed class Resource<T>{
    data class Success<T>(val data: T) : Resource<T>()
    data class Error<T>(val errorData: String):Resource<T>()
    data class Loading<T>(val loader: Boolean) : Resource<T>()
}

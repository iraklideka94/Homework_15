package com.example.homework_15.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_15.Resource.Resource
import com.example.homework_15.Retrofit.Retrofitinstance
import com.example.homework_15.model.Registration
import com.example.homework_15.model.RegistrationModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class RegistrationViewModel : ViewModel() {
    private val _registerState = MutableStateFlow<Resource<Registration>>(Resource.Loading(true))
    val registerState = _registerState.asStateFlow()


    fun register(email: String, password: String) {
        viewModelScope.launch {
            loginResponse(email = email, password = password).collect {
                _registerState.value = it
            }
        }
    }

    private fun loginResponse(email: String, password: String) = flow<Resource<Registration>> {
        emit(Resource.Loading(true))
        try {
            val response = Retrofitinstance.request()
                .getRegData(RegistrationModel(email = email, password = password))
            if (response.isSuccessful) {
                val body = response.body()
                emit(Resource.Success(body!!))
            } else {
                val error = response.errorBody()?.string()
                emit(Resource.Error(error!!))

            }
        } catch (e: Throwable) {
            emit(Resource.Error(e.toString()))
        }


    }
}
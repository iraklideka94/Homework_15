package com.example.homework_15.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_15.Resource.Resource
import com.example.homework_15.Retrofit.Retrofitinstance
import com.example.homework_15.model.Login
import com.example.homework_15.model.LoginModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _loginState = MutableStateFlow<Resource<Login>>(Resource.Loading(true))
    val loginState = _loginState.asStateFlow()


    fun getLogIn(email: String, password: String) {
        viewModelScope.launch {
            loginResponse(email = email, password = password).collect {
                _loginState.value = it
            }
        }
    }

    private fun loginResponse(email: String, password: String) = flow<Resource<Login>> {
        emit(Resource.Loading(true))
        try {
            val response = Retrofitinstance.request()
                .getLoginData(LoginModel(email = email, password = password))
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
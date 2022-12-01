package com.colddelight.github_info.presentaion.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import retrofit2.HttpException
import java.net.SocketException
import java.net.UnknownHostException

abstract class BaseViewModel : ViewModel() {
    private val _state = MutableLiveData<String>()
    val state: LiveData<String>
        get() = _state

    protected val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()

        when (throwable) {
            is SocketException -> _state.postValue("1err")
            is HttpException -> _state.postValue("2err")
            is UnknownHostException -> _state.postValue("3err")
            else -> _state.postValue("4err")
        }
    }
}
package com.colddelight.github_info.presentaion.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.colddelight.github_info.FetchState
import kotlinx.coroutines.CoroutineExceptionHandler
import retrofit2.HttpException
import java.net.SocketException
import java.net.UnknownHostException

abstract class BaseViewModel : ViewModel() {
    private val _state = MutableLiveData<Pair<Throwable, FetchState>>()
    val state: LiveData<Pair<Throwable, FetchState>> = _state


    protected val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
        when (throwable) {
            is SocketException -> _state.postValue(Pair(throwable,FetchState.BAD_INTERNET))
            is HttpException -> _state.postValue(Pair(throwable,FetchState.PARSE_ERROR))
            is UnknownHostException -> _state.postValue(Pair(throwable,FetchState.WRONG_CONNECTION))
            else -> _state.postValue(Pair(throwable,FetchState.FAIL))
        }
    }
}
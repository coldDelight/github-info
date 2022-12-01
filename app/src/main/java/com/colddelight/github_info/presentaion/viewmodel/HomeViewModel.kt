package com.colddelight.github_info.presentaion.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.colddelight.domain.model.DomainGithubUser
import com.colddelight.domain.use_case.GetUserInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: GetUserInfoUseCase
): ViewModel(){

    private val _user: MutableStateFlow<DomainGithubUser?> = MutableStateFlow(null)
    val user: StateFlow<DomainGithubUser?> = _user

    init {
        getUserInfo()
    }

    private fun getUserInfo() {
        viewModelScope.launch {
            val user = useCase.invoke()
            _user.value = user
        }
    }

}
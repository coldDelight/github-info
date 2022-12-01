package com.colddelight.github_info.presentaion.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.colddelight.domain.model.DomainGithubUser
import com.colddelight.domain.model.DomainRepo
import com.colddelight.domain.use_case.GetUserInfoUseCase
import com.colddelight.domain.use_case.GetUserReposUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val infoUseCase: GetUserInfoUseCase,
    private val reposUseCase: GetUserReposUseCase

): ViewModel(){

    private val _user: MutableStateFlow<DomainGithubUser?> = MutableStateFlow(null)
    val user: StateFlow<DomainGithubUser?> = _user

    private val _repos: MutableStateFlow<List<DomainRepo>?> = MutableStateFlow(null)
    val repos: StateFlow<List<DomainRepo>?> = _repos

    init {
        getUserInfo()
        getUserRepos()
    }

    private fun getUserInfo() {
        viewModelScope.launch {
            val user = infoUseCase.invoke()
            _user.value = user
        }
    }

    private fun getUserRepos() {
        viewModelScope.launch {
            val repos = reposUseCase.invoke()
            _repos.value = repos
        }
    }

}
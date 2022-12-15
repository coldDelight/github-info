package com.colddelight.github_info.presentaion.viewmodel

import androidx.lifecycle.viewModelScope
import com.colddelight.domain.model.DomainGithubUser
import com.colddelight.domain.model.DomainRepo
import com.colddelight.domain.model.DomainUserInfo
import com.colddelight.domain.use_case.GetUserInfoUseCase
import com.colddelight.domain.use_case.GetUserReposUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.SocketException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val infoUseCase: GetUserInfoUseCase,
    private val reposUseCase: GetUserReposUseCase

) : BaseViewModel() {

    private val _user: MutableStateFlow<DomainGithubUser?> = MutableStateFlow(null)
    private val _repos: MutableStateFlow<List<DomainRepo>?> = MutableStateFlow(null)

    private val _userState: MutableStateFlow<DomainUserInfo?> = MutableStateFlow(null)
    val userState: StateFlow<DomainUserInfo?> = _userState

    init {
        _user.combine(_repos) { user, repos ->
            _userState.value = DomainUserInfo(user, repos)
        }.launchIn(viewModelScope)

        getUserInfo()
        getUserRepos()
    }

    private fun getUserInfo() = viewModelScope.launch(exceptionHandler) {
        viewModelScope.launch {
            val user = infoUseCase.invoke()
            _user.value = user

//            throw SocketException()
        }
    }

    private fun getUserRepos() = viewModelScope.launch(exceptionHandler) {
        viewModelScope.launch {
            val repos = reposUseCase.invoke()
            _repos.value = repos
        }
    }

}
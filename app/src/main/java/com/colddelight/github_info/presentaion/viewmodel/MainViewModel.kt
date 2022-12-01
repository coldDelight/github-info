package com.colddelight.github_info.presentaion.viewmodel

import com.colddelight.domain.use_case.SaveTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCase: SaveTokenUseCase
): BaseViewModel(){
    fun setToken(token : String) {
        useCase.invoke(token)
    }
}
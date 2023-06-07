package com.demo.githublogin.presentation

import androidx.lifecycle.ViewModel
import com.demo.githublogin.domain.SaveLoginTokenUseCase
import com.demo.githublogin.domain.model.Token

class SaveUserLoginTokenViewModel(private val useCase: SaveLoginTokenUseCase) : ViewModel() {
    fun saveUserLoginToken(token: Token) {
        useCase(token)
    }
}

package com.demo.githublogin.domain

import com.demo.githublogin.domain.model.Token

class SaveLoginTokenUseCase(private val repository: GithubLoginRepository) {
    operator fun invoke(token: Token) {
        repository.saveUserLoginToken(token)
    }
}

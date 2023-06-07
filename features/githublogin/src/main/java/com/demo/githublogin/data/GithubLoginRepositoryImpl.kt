package com.demo.githublogin.data

import com.demo.githublogin.domain.GithubLoginDataSource
import com.demo.githublogin.domain.GithubLoginRepository
import com.demo.githublogin.domain.model.Token

class GithubLoginRepositoryImpl(
    private val local: GithubLoginDataSource.Local
) : GithubLoginRepository {
    override fun saveUserLoginToken(token: Token) {
        local.saveUserLoginToken(token)
    }
}
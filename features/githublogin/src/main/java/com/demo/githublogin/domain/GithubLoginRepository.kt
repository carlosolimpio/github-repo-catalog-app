package com.demo.githublogin.domain

import com.demo.githublogin.domain.model.Token

interface GithubLoginRepository {
    fun saveUserLoginToken(token: Token)
}

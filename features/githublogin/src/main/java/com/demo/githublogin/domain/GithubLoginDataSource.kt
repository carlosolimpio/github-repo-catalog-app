package com.demo.githublogin.domain

import com.demo.githublogin.domain.model.Token

interface GithubLoginDataSource {
    interface Local {
        fun saveUserLoginToken(token: Token)
    }
}

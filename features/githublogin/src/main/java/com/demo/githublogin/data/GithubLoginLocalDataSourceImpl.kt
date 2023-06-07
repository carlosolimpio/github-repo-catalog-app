package com.demo.githublogin.data

import androidx.security.crypto.EncryptedSharedPreferences
import com.demo.githublogin.domain.GithubLoginDataSource
import com.demo.githublogin.domain.model.Token

class GithubLoginLocalDataSourceImpl(
    private val prefs: EncryptedSharedPreferences
) : GithubLoginDataSource.Local {
    override fun saveUserLoginToken(token: Token) {
        with(prefs.edit()) {
            putString("TOKEN_TYPE", token.type)
            putString("TOKEN", token.token)
            apply()
        }
    }
}

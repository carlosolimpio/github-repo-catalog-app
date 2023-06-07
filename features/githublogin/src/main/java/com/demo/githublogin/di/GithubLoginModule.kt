package com.demo.githublogin.di

import android.net.Uri
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.demo.githublogin.BuildConfig
import com.demo.githublogin.data.GithubLoginLocalDataSourceImpl
import com.demo.githublogin.data.GithubLoginRepositoryImpl
import com.demo.githublogin.domain.GithubLoginDataSource
import com.demo.githublogin.domain.GithubLoginRepository
import com.demo.githublogin.domain.SaveLoginTokenUseCase
import com.demo.githublogin.presentation.SaveUserLoginTokenViewModel
import net.openid.appauth.AuthorizationRequest
import net.openid.appauth.AuthorizationService
import net.openid.appauth.AuthorizationServiceConfiguration
import net.openid.appauth.ClientSecretBasic
import net.openid.appauth.ResponseTypeValues
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private val authorizeUri = Uri.parse("https://github.com/login/oauth/authorize")
private val redirectUri = Uri.parse("auth://githubrepocatalog.app")
private val tokenUri = Uri.parse("https://github.com/login/oauth/access_token")

val authServiceModule = module {
    single { AuthorizationService(androidContext()) }
    single { AuthorizationServiceConfiguration(authorizeUri, tokenUri) }
    single { ClientSecretBasic(BuildConfig.GITHUB_CLIENT_SECRET) }

    factory {
        AuthorizationRequest
            .Builder(
                get(),
                BuildConfig.GITHUB_CLIENT_ID,
                ResponseTypeValues.CODE,
                redirectUri,
            )
            .setScopes("public_repo")
            .build()
    }
}

val prefsModule = module {
    single { MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC) }
    single {
        EncryptedSharedPreferences.create(
            "GithubLoginTokenPreferences",
            get(),
            androidContext(),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM,
        )
    }
}

val dataModule = module {
    factory<GithubLoginRepository> { GithubLoginRepositoryImpl(get()) }
    factory<GithubLoginDataSource.Local> { GithubLoginLocalDataSourceImpl(get()) }
}

val domainModule = module { factory { SaveLoginTokenUseCase(get()) } }

val presentationModule = module { factory { SaveUserLoginTokenViewModel(get()) } }

val loginModule = module {
    includes(authServiceModule, prefsModule, dataModule, domainModule, presentationModule)
}

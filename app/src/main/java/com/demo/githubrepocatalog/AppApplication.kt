package com.demo.githubrepocatalog

import android.app.Application
import com.demo.githublogin.di.loginModule
import com.demo.repolist.di.repoListModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AppApplication)
            modules(loginModule, repoListModule)
        }
    }
}

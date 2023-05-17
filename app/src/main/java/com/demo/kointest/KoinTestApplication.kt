package com.demo.kointest

import android.app.Application
import com.demo.kointest.di.appModules
import com.demo.testdata.di.appDataModule
import com.demo.testdomain.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KoinTestApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@KoinTestApplication)
            modules(appDataModule, domainModule, appModules)
        }
    }
}
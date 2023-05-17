package com.demo.testdata.data

import com.demo.testdata.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request()
                .newBuilder()
                .addHeader("Authorization", "Bearer ${BuildConfig.API_TOKEN}")
                .build()
        )
    }
}
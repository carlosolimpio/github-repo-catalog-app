package com.demo.testdata.di

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.demo.testdata.data.AuthorizationInterceptor
import com.demo.testdata.data.FetchDataRemoteDataSourceImpl
import com.demo.testdata.data.FetchDataRepositoryImpl
import com.demo.testdomain.domain.FetchDataDataSource
import com.demo.testdomain.domain.FetchDataRepository
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

private const val GITHUB_GRAPHQL_ENDPOINT = "https://api.github.com/graphql"

val networkModule = module {
    single<Interceptor> {
        AuthorizationInterceptor()
    }

    single {
        OkHttpClient.Builder()
            .writeTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .addNetworkInterceptor(get<Interceptor>())
            .build()
    }

    single {
        ApolloClient.Builder()
            .serverUrl(GITHUB_GRAPHQL_ENDPOINT)
            .okHttpClient(get())
            .build()
    }
}

val dataModule = module {
    factory<FetchDataDataSource> { FetchDataRemoteDataSourceImpl(get()) }
    factory<FetchDataRepository> { FetchDataRepositoryImpl(get()) }
}

val appDataModule = module {
    includes(networkModule, dataModule)
}


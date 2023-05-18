package com.demo.repolist.di

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.demo.repolist.data.GetRepoListRemoteDataSourceImpl
import com.demo.repolist.data.GetRepoListRepositoryImpl
import com.demo.repolist.data.network.AuthorizationInterceptor
import com.demo.repolist.domain.GetRepoListUseCase
import com.demo.repolist.domain.RepoListDataSource
import com.demo.repolist.domain.RepoListRepository
import com.demo.repolist.presentation.GetRepoListViewModel
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
    factory<RepoListRepository> { GetRepoListRepositoryImpl(get()) }
    factory<RepoListDataSource.Remote> { GetRepoListRemoteDataSourceImpl(get()) }
}

val domainModule = module { factory { GetRepoListUseCase(get()) } }

val presentationModule = module { factory { GetRepoListViewModel(get()) } }

val repoListModule = module {
    includes(networkModule, dataModule, domainModule, presentationModule)
}

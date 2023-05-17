package com.demo.testdata.data

import com.apollographql.apollo3.ApolloClient
import com.demo.data.SearchRepositoryQuery
import com.demo.testdomain.domain.FetchDataDataSource
import com.demo.testdomain.domain.model.GithubRepository
import com.demo.testdomain.domain.model.Owner
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class FetchDataRemoteDataSourceImpl(private val apiService: ApolloClient) : FetchDataDataSource {
    override suspend fun fetchDataFromApi() = flow {
        emit(
            apiService.query(
                SearchRepositoryQuery(10, "Android Kotlin")
            ).execute()
        )
    }.map { response ->
        val list = arrayListOf<GithubRepository>()

        response.data?.search?.nodes?.forEach { item ->
            val search = item?.onRepository

            val langs = arrayListOf<String>()
            search?.languages?.nodes?.forEach {
                it?.name?.let { name ->
                    langs.add(name)
                }
            }

            list.add(
                GithubRepository(
                    name = search?.name ?: "",
                    description = search?.description ?: "",
                    owner = Owner(
                        login = search?.owner?.login ?: "",
                        url = search?.owner?.url.toString(),
                        avatarUrl = search?.owner?.avatarUrl.toString(),
                    ),
                    url = search?.url.toString(),
                    createdAt = search?.createdAt.toString(),
                    homepageUrl = search?.homepageUrl.toString(),
                    languages = langs,
                    stargazerCount = search?.stargazerCount ?: 0,
                    isPrivate = search?.isPrivate ?: false,
                ),
            )
        }

        list
    }.flowOn(Dispatchers.IO)
}
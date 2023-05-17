package com.demo.testdomain.domain

import com.demo.testdomain.domain.model.GithubRepository
import kotlinx.coroutines.flow.Flow

interface FetchDataDataSource {
    suspend fun fetchDataFromApi(): Flow<List<GithubRepository>>
}
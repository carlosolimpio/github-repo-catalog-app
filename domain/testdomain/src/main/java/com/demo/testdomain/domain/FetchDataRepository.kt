package com.demo.testdomain.domain

import com.demo.testdomain.domain.model.GithubRepository
import kotlinx.coroutines.flow.Flow

interface FetchDataRepository {
    suspend fun fetchData(): Flow<List<GithubRepository>>
}
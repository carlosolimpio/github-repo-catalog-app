package com.demo.repolist.domain

import com.demo.testdomain.domain.model.GithubRepository
import kotlinx.coroutines.flow.Flow

interface RepoListRepository {
    fun getRepoListForQuery(query: String, itemCount: Int): Flow<List<GithubRepository>>
}

package com.demo.repolist.domain

import com.demo.testdomain.domain.model.GithubRepository
import kotlinx.coroutines.flow.Flow

class GetRepoListUseCase(private val repository: RepoListRepository) {
    operator fun invoke(query: String, itemCount: Int = 10): Flow<List<GithubRepository>> {
        return repository.getRepoListForQuery(query, itemCount)
    }
}
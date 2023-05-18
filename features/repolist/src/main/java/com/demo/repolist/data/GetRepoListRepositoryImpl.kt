package com.demo.repolist.data

import com.demo.repolist.domain.RepoListDataSource
import com.demo.repolist.domain.RepoListRepository

class GetRepoListRepositoryImpl(
    private val remote: RepoListDataSource.Remote
) : RepoListRepository {
    override fun getRepoListForQuery(query: String, itemCount: Int) =
        remote.getRepoListForQuery(query, itemCount)
}

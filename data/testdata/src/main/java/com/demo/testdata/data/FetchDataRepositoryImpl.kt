package com.demo.testdata.data

import com.demo.testdomain.domain.FetchDataDataSource
import com.demo.testdomain.domain.FetchDataRepository

class FetchDataRepositoryImpl(private val dataSource: FetchDataDataSource) : FetchDataRepository {
    override suspend fun fetchData() = dataSource.fetchDataFromApi()
}
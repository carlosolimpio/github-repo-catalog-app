package com.demo.testdomain.domain

class FetchDataUseCase(private val repository: FetchDataRepository) {
    suspend operator fun invoke() = repository.fetchData()
}

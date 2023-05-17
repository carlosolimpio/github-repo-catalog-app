package com.demo.testdomain.di

import com.demo.testdomain.domain.FetchDataUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { FetchDataUseCase(get()) }
}
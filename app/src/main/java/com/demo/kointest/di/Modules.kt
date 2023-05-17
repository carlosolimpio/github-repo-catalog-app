package com.demo.kointest.di

import com.demo.kointest.view.FetchDataViewModel
import org.koin.dsl.module

val viewModule = module {
    factory { FetchDataViewModel(get()) }
}

val appModules = module {
    includes(viewModule)
}
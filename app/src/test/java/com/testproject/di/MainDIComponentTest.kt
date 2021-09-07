package com.testproject.di

import com.testproject.data.di.repositoriesModule
import com.testproject.domain.di.useCasesModule

fun configureTestAppComponent(baseApi: String) =
    listOf(
        MockWebServerDITest,
        configureNetworkModuleForTest(baseApi),
        repositoriesModule,
        viewModelsModule,
        useCasesModule
    )

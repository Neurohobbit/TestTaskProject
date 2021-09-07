package com.testproject.di

import okhttp3.mockwebserver.MockWebServer
import org.koin.dsl.module

val MockWebServerDITest = module {

    factory {
        MockWebServer()
    }
}

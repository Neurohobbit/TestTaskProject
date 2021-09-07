package com.testproject.di

import com.google.gson.GsonBuilder
import com.testproject.domain.core.retrofit.ResultAdapterFactory
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.core.scope.Scope
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val CONNECT_TIMEOUT = 15L
private const val WRITE_TIMEOUT = 15L
private const val READ_TIMEOUT = 15L
private const val CACHE_MAX_SIZE = 10L * 1024 * 1024

/**
 * Network module test configuration with mockserver url.
 */
fun configureNetworkModuleForTest(baseApi: String) = module {
    single { Cache(androidApplication().cacheDir, CACHE_MAX_SIZE) }
    single { GsonBuilder().create() }
    single { retrofitHttpClient() }
    single {
        Retrofit.Builder()
            .baseUrl(baseApi)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .addCallAdapterFactory(ResultAdapterFactory())
            .client(get())
            .build()
    }
    single {
        Interceptor { chain ->
            chain.proceed(
                chain.request().newBuilder().apply {
                    header(
                        "Accept", "application/json"
                    )
                }.build()
            )
        }
    }
}

private fun Scope.retrofitHttpClient(): OkHttpClient {
    return OkHttpClient.Builder().apply {
        cache(get())
        connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        retryOnConnectionFailure(true)
    }.build()
}

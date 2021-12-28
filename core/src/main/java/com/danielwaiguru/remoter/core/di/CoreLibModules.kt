package com.danielwaiguru.remoter.core.di

import com.danielwaiguru.remoter.core.BuildConfig
import com.danielwaiguru.remoter.core.data.remote.RemotiveApiService
import com.danielwaiguru.remoter.core.data.repositories.JobsRepositoryImpl
import com.danielwaiguru.remoter.core.domain.repositories.JobsRepository
import com.danielwaiguru.remoter.core.domain.use_cases.GetJobsUseCase
import com.danielwaiguru.remoter.shared.utils.ApiConstants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

private val networkingModule = module {
    factory { provideOkHttpClient() }
    single { provideRetrofitInstance(get()) }
    single { provideRemotiveApiService(get()) }
}

fun provideOkHttpClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = when (BuildConfig.BUILD_TYPE) {
        "release" -> HttpLoggingInterceptor.Level.NONE
        else -> HttpLoggingInterceptor.Level.BODY
    }
    return OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()
}
fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
}
fun provideRemotiveApiService(retrofit: Retrofit): RemotiveApiService {
    return retrofit.create(RemotiveApiService::class.java)
}
private val useCasesModules = module {
    single { GetJobsUseCase(get()) }
}
private val repoModules = module {
    single<JobsRepository>{ JobsRepositoryImpl(get()) }
}

val coreLibModules = listOf(
    networkingModule,
    useCasesModules,
    repoModules
)
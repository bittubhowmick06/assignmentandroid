package com.example.assignmentandroid.di

import com.example.assignmentandroid.data.remote.ApiClient
import com.example.assignmentandroid.data.remote.AppApiHelper
import com.example.assignmentandroid.data.remote.repository.ApiRepositoryHelper
import com.example.assignmentandroid.data.remote.repository.ApiRepositoryImpl
import com.example.assignmentandroid.util.AppConstant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideRemoteApi(): ApiClient = Retrofit.Builder()
        .baseUrl(AppConstant().BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiClient::class.java)

    @Singleton
    @Provides
    fun provideRepository(api: ApiClient): ApiRepositoryHelper {
        return ApiRepositoryImpl(api)
    }

    @Singleton
    @Provides
    fun provideDailyQuoteUseCase(repository: ApiRepositoryHelper): AppApiHelper {
        return AppApiHelper(repository)
    }
}
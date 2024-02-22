package com.example.imgurimagesearchv2.di

import com.example.imgurimagesearchv2.data.remote.ApiService
import com.example.imgurimagesearchv2.data.repository.ImgurRepositoryImpl
import com.example.imgurimagesearchv2.presentation.repository.ImgurRepository
import com.example.imgurimagesearchv2.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideApiService() : ApiService{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    fun provideImgurRepository(apiService: ApiService): ImgurRepository{
        return ImgurRepositoryImpl(apiService)
    }

}
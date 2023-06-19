package com.muzo.musicapp.core.data.di

import com.muzo.musicapp.core.constants.Constants.BASE_URL
import com.muzo.musicapp.core.data.remote.api.ResultService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient).build()
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideMusicService(retrofit: Retrofit):ResultService{
        return retrofit.create(ResultService::class.java)
    }

}
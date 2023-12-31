package com.muzo.musicapp.core.data.di

import android.content.Context
import androidx.room.Room
import com.muzo.musicapp.core.constants.Constants.BASE_URL
import com.muzo.musicapp.core.constants.Constants.DATABASE_NAME
import com.muzo.musicapp.core.data.local.room.MusicDataBase
import com.muzo.musicapp.core.data.remote.api.ResultService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }


    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }


    @Provides
    @Singleton
    fun provideMusicService(retrofit: Retrofit): ResultService {
        return retrofit.create(ResultService::class.java)
    }

    @Singleton
    @Provides
    fun provideRunDao(db: MusicDataBase) = db.getMusicDao()


    @Provides
    @Singleton
    fun provideMusicDataBase(@ApplicationContext app: Context) =
        Room.databaseBuilder(app, MusicDataBase::class.java, DATABASE_NAME).build()


}
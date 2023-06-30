package com.muzo.musicapp.core.data.di

import com.muzo.musicapp.core.data.local.repository.LocalMainRepository
import com.muzo.musicapp.core.data.local.repository.LocalMainRepositoryImpl
import com.muzo.musicapp.core.data.local.source.LocalMusicDataSource
import com.muzo.musicapp.core.data.local.source.LocalMusicDataSourceImpl
import com.muzo.musicapp.core.data.remote.repository.MusicRepository
import com.muzo.musicapp.core.data.remote.repository.MusicRepositoryImpl
import com.muzo.musicapp.core.data.remote.source.MusicRemoteDataSource
import com.muzo.musicapp.core.data.remote.source.MusicRemoteDataSourceImpl
import com.muzo.musicapp.core.data.remote.source.pagination.ItunesSearchRepository
import com.muzo.musicapp.core.data.remote.source.pagination.ItunesSearchRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface MusicModule {

    @Binds
    fun bindsMusicRemoteService(
        sourceImpl: MusicRemoteDataSourceImpl
    ):MusicRemoteDataSource

    @Binds
    fun bindMusicRepository(
        musicRepositoryImpl: MusicRepositoryImpl
    ):MusicRepository

    @Binds
    fun bindPaginationRepository(
        pagingRepositoryImpl: ItunesSearchRepositoryImpl
    ):ItunesSearchRepository

    @Binds
    fun bindLocalMainRepository(
        localMainRepositoryImpl: LocalMainRepositoryImpl
    ):LocalMainRepository


    @Binds
    fun bindLocalDataSource(
        localMusicDataSourceImpl: LocalMusicDataSourceImpl
    ):LocalMusicDataSource

}
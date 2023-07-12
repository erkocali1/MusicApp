package com.muzo.musicapp.core.data.local.repository

import com.muzo.musicapp.core.data.local.room.modelclass.FavLocalData
import com.muzo.musicapp.core.data.local.room.modelclass.LastClikedMusic
import com.muzo.musicapp.core.data.local.room.modelclass.MusicLocalData
import com.muzo.musicapp.core.data.local.source.LocalMusicDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalMainRepositoryImpl @Inject constructor(
    private val musicDataSource: LocalMusicDataSource
) : LocalMainRepository {
    override suspend fun saveMusicList(musicList: List<MusicLocalData>) {
        return musicDataSource.insertMusic(musicList)
    }

    override suspend fun deleteMusicById(musicId: Int) {
        return musicDataSource.deleteMusicByUid(musicId)
    }

    override suspend fun getAllDataFromRoom(): Flow<List<MusicLocalData>> {
        return musicDataSource.getMusicList()
    }

    override suspend fun saveLastClickedMusic(lastClikedMusic: List<LastClikedMusic>) {
        return musicDataSource.insertLastClickedMusic(lastClikedMusic)
    }

    override suspend fun getallLastClickedMusic(): Flow<List<LastClikedMusic>> {
        return musicDataSource.getLastClickedMusic()
    }

    override suspend fun saveFavMusic(lastClikedMusic: List<FavLocalData>) {
        return musicDataSource.insertFavMusic(lastClikedMusic)
    }

    override suspend fun getallFavMusic(): Flow<List<FavLocalData>> {
        return musicDataSource.getFavMusic()
    }

    override suspend fun deleteFavMusicByTrackName(trackName: String) {
        return musicDataSource.deleteFavMusicByTrackName(trackName)
    }

    override suspend fun deleteAllFav() {
        return musicDataSource.deleteAllFav()
    }


}
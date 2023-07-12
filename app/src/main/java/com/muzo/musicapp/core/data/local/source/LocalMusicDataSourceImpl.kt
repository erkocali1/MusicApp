package com.muzo.musicapp.core.data.local.source

import com.muzo.musicapp.core.data.local.MusicDao.MusicDao
import com.muzo.musicapp.core.data.local.room.modelclass.FavLocalData
import com.muzo.musicapp.core.data.local.room.modelclass.LastClikedMusic
import com.muzo.musicapp.core.data.local.room.modelclass.MusicLocalData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalMusicDataSourceImpl @Inject constructor(
    private val musicDao: MusicDao
) : LocalMusicDataSource {
    override suspend fun getMusicList(): Flow<List<MusicLocalData>> {
        return musicDao.getAllMusic()
    }

    override suspend fun insertMusic(musicList: List<MusicLocalData>) {
        return musicDao.insertMusic(musicList)
    }

    override suspend fun deleteMusicByUid(musicId: Int) {
       return musicDao.deleteMusicByUid(musicId)
    }

    override suspend fun getLastClickedMusic(): Flow<List<LastClikedMusic>> {
        return musicDao.getAllLastClickedMusic()
    }

    override suspend fun insertLastClickedMusic(lastClickedMusic: List<LastClikedMusic>) {
        return musicDao.insertLastClickedMusic(lastClickedMusic)
    }

    override suspend fun getFavMusic(): Flow<List<FavLocalData>> {
        return musicDao.getAllFavMusic()

    }

    override suspend fun insertFavMusic(favMusic: List<FavLocalData>) {
        return musicDao.insertFavMusic(favMusic)
    }

    override suspend fun deleteFavMusicByTrackName(trackName: String) {
        return musicDao.deleteFavMusicByTrackName(trackName)
    }

    override suspend fun deleteAllFav() {
        return musicDao.deleteAllFavMusic()
    }


}
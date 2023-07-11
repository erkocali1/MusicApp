package com.muzo.musicapp.feature.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muzo.musicapp.core.data.local.room.modelclass.LastClikedMusic
import com.muzo.musicapp.core.data.local.room.modelclass.MusicLocalData
import com.muzo.musicapp.core.data.model.Music
import com.muzo.musicapp.databinding.ItemRow2Binding


class ThirdPageAdapter(var musicList:List<LastClikedMusic>,val onMusicClickListener: (item: LastClikedMusic) -> Unit ): RecyclerView.Adapter<ThirdPageAdapter.ViewHolder>() {


    inner class ViewHolder(private val binding : ItemRow2Binding): RecyclerView.ViewHolder(binding.root){

        fun bind(item: LastClikedMusic){

            binding.apply {
                signerName.text=item.artistName
                songName.text=item.trackName
                root.setOnClickListener {
                    onMusicClickListener(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding= ItemRow2Binding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentItem=musicList[position]
        holder.bind(currentItem)

    }

    override fun getItemCount(): Int {
        return musicList.size
    }

}


package com.muzo.musicapp.feature.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muzo.musicapp.core.data.local.room.MusicLocalData
import com.muzo.musicapp.databinding.ItemRow2Binding


class ThirdPageAdapter(var musicList:List<MusicLocalData>): RecyclerView.Adapter<ThirdPageAdapter.ViewHolder>() {


    inner class ViewHolder(private val binding : ItemRow2Binding): RecyclerView.ViewHolder(binding.root){

        fun bind(item: MusicLocalData){

            binding.apply {
                signerName.text=item.artistName
                songName.text=item.trackName
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


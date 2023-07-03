package com.muzo.musicapp.feature.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.muzo.musicapp.core.data.local.room.MusicLocalData
import com.muzo.musicapp.core.data.model.PaginationList
import com.muzo.musicapp.databinding.ItemRow3Binding


class ForthPageAdapter(var musicList:List<MusicLocalData>,val onDeleteClickListener: (item: MusicLocalData) -> Unit): RecyclerView.Adapter<ForthPageAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding : ItemRow3Binding): RecyclerView.ViewHolder(binding.root){

        fun bind(item: MusicLocalData){

            binding.apply {
                artistName.text=item.artistName
                trackName.text=item.trackName
                releaseDate.text=item.releaseDate
                trackPrice.text=item.trackPrice

                val imageLink=item.artworkUrl100

                IvSigner.load(imageLink){
                    crossfade(true)
                    crossfade(1000)

                    icDelete.setOnClickListener {
                        onDeleteClickListener(item)
                    }
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding= ItemRow3Binding.inflate(LayoutInflater.from(parent.context),parent,false)
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
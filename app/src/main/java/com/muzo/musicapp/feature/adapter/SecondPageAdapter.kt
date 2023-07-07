package com.muzo.musicapp.feature.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.muzo.musicapp.core.data.model.Music
import com.muzo.musicapp.databinding.ItemRow2Binding
import com.muzo.musicapp.databinding.ItemRow5Binding

class SecondPageAdapter(var musicList:List<Music>):RecyclerView.Adapter<SecondPageAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding : ItemRow5Binding):RecyclerView.ViewHolder(binding.root){

        fun bind(item:Music){

            binding.apply {
                signerName.text=item.artistName
                songName.text=item.trackName
                val url=item.artworkUrl100
                IvSigner.load(url)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding=ItemRow5Binding.inflate(LayoutInflater.from(parent.context),parent,false)
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
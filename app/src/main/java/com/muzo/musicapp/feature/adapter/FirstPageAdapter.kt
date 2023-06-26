package com.muzo.musicapp.feature.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.muzo.musicapp.core.data.model.Music
import com.muzo.musicapp.core.data.model.PaginationList

import com.muzo.musicapp.databinding.ItemRowBinding

class FirstPageAdapter : PagingDataAdapter<PaginationList, FirstPageAdapter.MyViewHolder>(diffCallback) {


    inner class MyViewHolder(val binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root)

    companion object {

        val diffCallback = object : DiffUtil.ItemCallback<PaginationList>() {
            override fun areItemsTheSame(oldItem: PaginationList, newItem: PaginationList): Boolean {
                return oldItem.artistId == newItem.artistId
            }

            override fun areContentsTheSame(oldItem: PaginationList, newItem: PaginationList): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem= getItem(position)

        holder.binding.apply {
            teext.text=currentItem?.artistName
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       return MyViewHolder(ItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }


}
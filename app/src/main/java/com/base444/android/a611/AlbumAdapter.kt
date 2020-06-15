package com.base444.android.a611

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class AlbumAdapter(
    var albumList: List<Album>,
    var onItemClickInterface: OnItemClickInterface
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface OnItemClickInterface{
        fun onItemClick(index: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AlbumViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.grid_cell, parent, false))
    }
    override fun getItemCount(): Int = albumList.size

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val albunViewHolder = viewHolder as AlbumViewHolder
        albunViewHolder.bindView(albumList.get(position))
        albunViewHolder.itemView.setOnClickListener {
            onItemClickInterface.onItemClick(position)
        }
    }
}
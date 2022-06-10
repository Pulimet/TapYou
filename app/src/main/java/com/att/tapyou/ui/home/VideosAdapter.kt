package com.att.tapyou.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.att.tapyou.R

class VideosAdapter : ListAdapter<String, VideoHolder>(VideoDiff()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent, false)
        return VideoHolder(v)
    }

    override fun onBindViewHolder(holder: VideoHolder, position: Int) {
        holder.onBindViewHolder(getItem(position))
    }
}

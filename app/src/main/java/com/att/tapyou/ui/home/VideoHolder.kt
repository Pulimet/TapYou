package com.att.tapyou.ui.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.att.tapyou.databinding.ItemVideoBinding

class VideoHolder(v: View) : RecyclerView.ViewHolder(v) {

    private val binding = ItemVideoBinding.bind(v)

    fun onBindViewHolder(id: String?) {
        binding.tvId.text = id
    }

}
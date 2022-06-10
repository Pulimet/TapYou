package com.att.tapyou.ui.home

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.att.tapyou.databinding.ItemVideoBinding

class VideoHolder(v: View) : RecyclerView.ViewHolder(v) {

    private val binding = ItemVideoBinding.bind(v)

    @SuppressLint("SetJavaScriptEnabled")
    fun onBindViewHolder(id: String?) {
        val data =
            "<iframe width='560' height='315' src='https://www.youtube.com/embed/$id' frameborder='0' allow='accelerometer; encrypted-media; gyroscope; picture-in-picture' allowfullscreen></iframe>"
        binding.webView.apply {
            settings.javaScriptEnabled = true
            loadData(data, "text/html; charset=utf-8", "UTF-8")
        }
    }

}
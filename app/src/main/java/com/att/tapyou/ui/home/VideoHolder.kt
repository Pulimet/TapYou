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
            "<iframe src='https://www.youtube-nocookie.com/embed/$id?rel=0&autohide=1&showinfo=0&controls=0&fs=0' width='400' height='280' frameborder='0' modestbranding='1'></iframe>"
        binding.webView.apply {
            settings.javaScriptEnabled = true
            loadData(data, "text/html; charset=utf-8", "UTF-8")
        }

        // TODO Inject CSS code to hide YouTube overlays
    }
}
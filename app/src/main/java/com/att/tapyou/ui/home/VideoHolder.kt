package com.att.tapyou.ui.home

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.att.tapyou.databinding.ItemVideoBinding

class VideoHolder(v: View) : RecyclerView.ViewHolder(v) {

    private val binding = ItemVideoBinding.bind(v)

    // TODO Dear reviewing developer please note that last commit (actually the change in the code below)
    //  was done after timeout, and was added after I received a confirmation from Maria.

    @SuppressLint("SetJavaScriptEnabled")
    fun onBindViewHolder(id: String?) {
        val data = """
                    <html>
                    <head></head>
                    <body>
                    <div style="height:400px; width:400px; overflow: hidden;">
                        <iframe src="https://www.youtube.com/embed/$id?theme=dark&autoplay=1&autohide=0&cc_load_policy=1&modestbranding=1&fs=0&showinfo=0&rel=0&iv_load_policy=3&mute=0&loop=1" 
                        style="width: 400px; height:400px; position:relative; bottom:60px;">
                        </iframe>
                    </div>
                    </body>
                    </html>
                     """
        binding.webView.apply {
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            settings.allowContentAccess = true
            loadData(data, "text/html; charset=utf-8", "UTF-8")
        }
    }
}
package com.yml.womensafety.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.recyclerview.widget.RecyclerView
import com.yml.womensafety.R
import com.yml.womensafety.navigationdrawer.HelplineNumbers
import com.yml.womensafety.navigationdrawer.youtube.YouTubeVideos

class VideoAdapter :
    RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {
    private val youtubeVideoList = mutableListOf<YouTubeVideos>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        return VideoViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.video_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.videoWeb.loadData(youtubeVideoList[position].videoUrl, "text/html", "utf-8")
    }

    @SuppressLint("SetJavaScriptEnabled")
    class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var videoWeb: WebView = itemView.findViewById(R.id.webView)

        init {
            videoWeb.settings.javaScriptEnabled = true
        }
    }

    override fun getItemCount(): Int {
        return youtubeVideoList.size
    }
    fun setYouTubeVideos(youtubeVideoList: List<YouTubeVideos>) {
        this.youtubeVideoList.clear()
        this.youtubeVideoList.addAll(youtubeVideoList)
        notifyDataSetChanged()
    }
}
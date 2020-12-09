package com.yml.womensafety.navigationdrawer.youtube

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yml.womensafety.R
import java.util.*
class SelfDefenseVideoFragment : Fragment(R.layout.fragment_self_defense_video) {
    private lateinit var recyclerView: RecyclerView
    private var youtubeVideos = Vector<YouTubeVideos>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerViewVideos)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        youtubeVideos.add(YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www" +
                ".youtube.com/embed/T7aNSRoDCmg\" frameborder=\"0\" allowfullscreen></iframe>"))
        youtubeVideos.add(YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www" +
                ".youtube.com/embed/k9Jn0eP-ZVg\" frameborder=\"0\" allowfullscreen></iframe>"))
        youtubeVideos.add(YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www" +
                ".youtube.com/embed/KVpxP3ZZtAc\" frameborder=\"0\" allowfullscreen>lt;/iframe>"))
        youtubeVideos.add(YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www" +
                ".youtube.com/embed/-V4vEyhWDZ0\" frameborder=\"0\" allowfullscreen></iframe>"))
        val videoAdapter = VideoAdapter(youtubeVideos)
        recyclerView.adapter = videoAdapter

    }


}
package com.yml.womensafety.repository

import androidx.lifecycle.MutableLiveData
import com.yml.womensafety.navigationdrawer.youtube.YouTubeVideos
import java.util.*

class YouTubeRepository {
    private var videosDataSet = Vector<YouTubeVideos>()

    fun getYouTubeVideos(): MutableLiveData<List<YouTubeVideos>> {
        setYouTubeVideos()
        val videosData: MutableLiveData<List<YouTubeVideos>> = MutableLiveData()
        videosData.value = videosDataSet
        return videosData
    }

    private fun setYouTubeVideos() {
        videosDataSet.add(
            YouTubeVideos(
                "<iframe width=\"100%\" height=\"100%\" src=\"https://www" +
                        ".youtube.com/embed/T7aNSRoDCmg\" frameborder=\"0\" allowfullscreen></iframe>"
            )
        )
        videosDataSet.add(
            YouTubeVideos(
                "<iframe width=\"100%\" height=\"100%\" src=\"https://www" +
                        ".youtube.com/embed/k9Jn0eP-ZVg\" frameborder=\"0\" allowfullscreen></iframe>"
            )
        )
        videosDataSet.add(
            YouTubeVideos(
                "<iframe width=\"100%\" height=\"100%\" src=\"https://www" +
                        ".youtube.com/embed/KVpxP3ZZtAc\" frameborder=\"0\" allowfullscreen>lt;/iframe>"
            )
        )
        videosDataSet.add(
            YouTubeVideos(
                "<iframe width=\"100%\" height=\"100%\" src=\"https://www" +
                        ".youtube.com/embed/-V4vEyhWDZ0\" frameborder=\"0\" allowfullscreen></iframe>"
            )
        )
    }
}
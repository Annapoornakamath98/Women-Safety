package com.yml.womensafety.repository

import com.yml.womensafety.navigationdrawer.youtube.YouTubeVideos

class YouTubeRepository {
    private var videosDataSet = ArrayList<YouTubeVideos>()

    fun getYouTubeVideos(): ArrayList<YouTubeVideos> {
        setYouTubeVideos()
        return videosDataSet
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
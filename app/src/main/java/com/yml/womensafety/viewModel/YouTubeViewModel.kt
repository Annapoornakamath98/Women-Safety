package com.yml.womensafety.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yml.womensafety.navigationdrawer.youtube.YouTubeVideos
import com.yml.womensafety.repository.YouTubeRepository

class YouTubeViewModel : ViewModel() {
    lateinit var youTubeRepository: YouTubeRepository
    private var youTubeVideosDataSet = ArrayList<YouTubeVideos>()
    var youTubeVideosData = MutableLiveData<ArrayList<YouTubeVideos>>()

    fun initializeRepository() {
        youTubeRepository = YouTubeRepository()
        youTubeVideosDataSet = youTubeRepository.getYouTubeVideos()
    }

    fun getYouTubeVideos(): LiveData<ArrayList<YouTubeVideos>> {
        youTubeVideosData.value = youTubeVideosDataSet
        return youTubeVideosData
    }
}
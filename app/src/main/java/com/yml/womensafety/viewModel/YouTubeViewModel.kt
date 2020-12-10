package com.yml.womensafety.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yml.womensafety.navigationdrawer.youtube.YouTubeVideos
import com.yml.womensafety.repository.YouTubeRepository

class YouTubeViewModel : ViewModel() {
    lateinit var youTubeVideos: MutableLiveData<List<YouTubeVideos>>
    fun initializeRepository() {
        val youTubeRepository = YouTubeRepository()
        youTubeVideos = youTubeRepository.getYouTubeVideos()
    }

    fun getYouTubeVideos(): LiveData<List<YouTubeVideos>> {
        return youTubeVideos
    }
}
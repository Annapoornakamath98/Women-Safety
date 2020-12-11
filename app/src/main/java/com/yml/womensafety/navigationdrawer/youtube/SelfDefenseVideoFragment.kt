package com.yml.womensafety.navigationdrawer.youtube

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yml.womensafety.R
import com.yml.womensafety.adapters.VideoAdapter
import com.yml.womensafety.viewModel.YouTubeViewModel

class SelfDefenseVideoFragment : Fragment(R.layout.fragment_self_defense_video) {
    private lateinit var videoRecyclerView: RecyclerView
    lateinit var youTubeViewModel: YouTubeViewModel
    private lateinit var videoAdapter: VideoAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        youTubeViewModel = ViewModelProvider(this).get(YouTubeViewModel::class.java)
        youTubeViewModel.initializeRepository()
        youTubeViewModel.getYouTubeVideos().observe(viewLifecycleOwner, {
            videoAdapter.notifyDataSetChanged()
        })
        initializeRecyclerView()
    }

    private fun initializeRecyclerView() {
        videoAdapter = VideoAdapter(youTubeViewModel.getYouTubeVideos().value!!)
        videoRecyclerView = view?.findViewById(R.id.recyclerViewVideos)!!
        videoRecyclerView.setHasFixedSize(true)
        videoRecyclerView.layoutManager = LinearLayoutManager(view?.context)
        videoRecyclerView.adapter = videoAdapter
    }


}
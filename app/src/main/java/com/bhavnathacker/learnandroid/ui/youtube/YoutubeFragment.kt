package com.bhavnathacker.learnandroid.ui.youtube

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bhavnathacker.learnandroid.R
import com.bhavnathacker.learnandroid.utils.CustomItemDecoration
import kotlinx.android.synthetic.main.fragment_youtube.*

class YoutubeFragment : Fragment() {

    private lateinit var youtubeViewModel: YoutubeViewModel
    private var adapter = YoutubeAdapter(listOf())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        youtubeViewModel =
            ViewModelProvider(this).get(YoutubeViewModel::class.java)
        return inflater.inflate(R.layout.fragment_youtube, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        youtubeRecyclerView.layoutManager = LinearLayoutManager(activity)
        youtubeRecyclerView.adapter = adapter
        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.size_16)
        val itemDecoration =
            CustomItemDecoration(
                spacingInPixels
            )
        youtubeRecyclerView.addItemDecoration(itemDecoration)
        youtubeViewModel.channels.observe(viewLifecycleOwner, Observer {
            adapter.setItems(it)
        })
    }
}
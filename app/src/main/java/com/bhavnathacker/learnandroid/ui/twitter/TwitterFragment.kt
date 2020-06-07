package com.bhavnathacker.learnandroid.ui.twitter

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
import kotlinx.android.synthetic.main.fragment_twitter.*

class TwitterFragment : Fragment() {

    private lateinit var twitterViewModel: TwitterViewModel
    private var adapter = TwitterAdapter(listOf())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        twitterViewModel =
            ViewModelProvider(this).get(TwitterViewModel::class.java)
        return inflater.inflate(R.layout.fragment_twitter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        twitterRecyclerView.layoutManager = LinearLayoutManager(activity)
        twitterRecyclerView.adapter = adapter
        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.size_16)
        val itemDecoration =
            CustomItemDecoration(
                spacingInPixels
            )
        twitterRecyclerView.addItemDecoration(itemDecoration)
        twitterViewModel.entries.observe(viewLifecycleOwner, Observer {
            adapter.setItems(it)
        })
    }
}
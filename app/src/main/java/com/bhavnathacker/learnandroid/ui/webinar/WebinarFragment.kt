package com.bhavnathacker.learnandroid.ui.webinar

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
import kotlinx.android.synthetic.main.fragment_webinars.*

class WebinarFragment : Fragment() {

    private lateinit var webinarsViewModel: WebinarViewModel
    private var adapter = WebinarAdapter(listOf())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        webinarsViewModel =
            ViewModelProvider(this).get(WebinarViewModel::class.java)
        return inflater.inflate(R.layout.fragment_webinars, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webinarRecyclerView.layoutManager = LinearLayoutManager(activity)
        webinarRecyclerView.adapter = adapter
        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.size_16)
        val itemDecoration =
            CustomItemDecoration(
                spacingInPixels
            )
        webinarRecyclerView.addItemDecoration(itemDecoration)
        webinarsViewModel.sesions.observe(viewLifecycleOwner, Observer {
            adapter.setItems(it)
        })
    }
}
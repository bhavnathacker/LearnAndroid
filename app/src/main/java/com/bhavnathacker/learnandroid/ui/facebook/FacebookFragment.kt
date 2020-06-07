package com.bhavnathacker.learnandroid.ui.facebook

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
import kotlinx.android.synthetic.main.fragment_facebook.*

class FacebookFragment : Fragment() {

    private lateinit var facebookViewModel: FacebookViewModel
    private var adapter = FacebookAdapter(listOf())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        facebookViewModel =
            ViewModelProvider(this).get(FacebookViewModel::class.java)
        return inflater.inflate(R.layout.fragment_facebook, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        facebookRecyclerView.layoutManager = LinearLayoutManager(activity)
        facebookRecyclerView.adapter = adapter
        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.size_16)
        val itemDecoration =
            CustomItemDecoration(
                spacingInPixels
            )
        facebookRecyclerView.addItemDecoration(itemDecoration)
        facebookViewModel.entries.observe(viewLifecycleOwner, Observer {
            adapter.setItems(it)
        })
    }
}
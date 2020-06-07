package com.bhavnathacker.learnandroid.ui.codelabs

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
import kotlinx.android.synthetic.main.fragment_codelabs.*

class CodeLabFragment : Fragment() {

    private lateinit var codelabsViewModel: CodelabViewModel
    private var adapter = CodelabAdapter(listOf())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        codelabsViewModel =
            ViewModelProvider(this).get(CodelabViewModel::class.java)
        return inflater.inflate(R.layout.fragment_codelabs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        codeLabsRecyclerView.layoutManager = LinearLayoutManager(activity)
        codeLabsRecyclerView.adapter = adapter
        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.size_16)
        val itemDecoration =
            CustomItemDecoration(
                spacingInPixels
            )
        codeLabsRecyclerView.addItemDecoration(itemDecoration)
        codelabsViewModel.levels.observe(viewLifecycleOwner, Observer {
            adapter.setItems(it)
        })
    }
}
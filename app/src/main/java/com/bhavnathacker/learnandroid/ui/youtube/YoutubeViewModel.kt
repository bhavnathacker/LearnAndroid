package com.bhavnathacker.learnandroid.ui.youtube

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class YoutubeViewModel : ViewModel() {

    private val repository = YoutubeRepository()

    private val _channels = MutableLiveData<List<YoutubeChannel>>().apply {
        value = loadYoutubeChannels()
    }

    private fun loadYoutubeChannels(): List<YoutubeChannel> {
        return repository.getYoutubeChannels()
    }

    val channels: LiveData<List<YoutubeChannel>> = _channels
}
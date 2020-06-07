package com.bhavnathacker.learnandroid.ui.webinar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WebinarViewModel : ViewModel() {

    private val repository = WebinarRepository()

    private val _sessions = MutableLiveData<List<Webinar>>().apply {
        value = loadWebinars()
    }

    private fun loadWebinars(): List<Webinar> {
        return repository.getWebinars()
    }

    val sesions: LiveData<List<Webinar>> = _sessions
}
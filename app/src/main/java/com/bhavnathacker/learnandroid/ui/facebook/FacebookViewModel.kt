package com.bhavnathacker.learnandroid.ui.facebook

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FacebookViewModel : ViewModel() {

    private val repository = FacebookRepository()

    private val _entries = MutableLiveData<List<Facebook>>().apply {
        value = loadFacebookEntries()
    }

    private fun loadFacebookEntries(): List<Facebook> {
        return repository.getFacebookEntries()
    }

    val entries: LiveData<List<Facebook>> = _entries
}
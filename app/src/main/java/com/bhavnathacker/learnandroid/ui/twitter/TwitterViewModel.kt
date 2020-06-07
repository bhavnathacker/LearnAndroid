package com.bhavnathacker.learnandroid.ui.twitter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TwitterViewModel : ViewModel() {

    private val repository = TwitterRepository()

    private val _entries = MutableLiveData<List<Twitter>>().apply {
        value = loadTwitterEntries()
    }

    private fun loadTwitterEntries(): List<Twitter> {
        return repository.getTwitterEntries()
    }

    val entries: LiveData<List<Twitter>> = _entries
}
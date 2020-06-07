package com.bhavnathacker.learnandroid.ui.codelabs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CodelabViewModel : ViewModel() {

    private val repository = CodelabRepository()

    private val _levels = MutableLiveData<List<CodelabLevel>>().apply {
        value = loadCodelabLevels()
    }

    private fun loadCodelabLevels(): List<CodelabLevel> {
        return repository.getCodelabLevels()
    }

    val levels: LiveData<List<CodelabLevel>> = _levels
}
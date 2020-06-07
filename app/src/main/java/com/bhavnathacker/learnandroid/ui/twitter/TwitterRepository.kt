package com.bhavnathacker.learnandroid.ui.twitter

import com.bhavnathacker.learnandroid.utils.AppUtils

class TwitterRepository {
    fun getTwitterEntries(): List<Twitter> {
        return AppUtils.getTwitterEntries()
    }
}
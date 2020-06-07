package com.bhavnathacker.learnandroid.ui.facebook

import com.bhavnathacker.learnandroid.utils.AppUtils

class FacebookRepository {
    fun getFacebookEntries(): List<Facebook> {
        return AppUtils.getFacebookEntries()
    }
}
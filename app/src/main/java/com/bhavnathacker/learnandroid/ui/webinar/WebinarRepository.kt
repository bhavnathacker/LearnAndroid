package com.bhavnathacker.learnandroid.ui.webinar

import com.bhavnathacker.learnandroid.utils.AppUtils

class WebinarRepository {
    fun getWebinars(): List<Webinar> {
        return AppUtils.getWebinars()
    }
}
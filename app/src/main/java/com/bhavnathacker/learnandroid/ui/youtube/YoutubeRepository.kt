package com.bhavnathacker.learnandroid.ui.youtube

import com.bhavnathacker.learnandroid.utils.AppUtils

class YoutubeRepository {
    fun getYoutubeChannels(): List<YoutubeChannel> {
        return AppUtils.getYoutubeChannels()
    }
}
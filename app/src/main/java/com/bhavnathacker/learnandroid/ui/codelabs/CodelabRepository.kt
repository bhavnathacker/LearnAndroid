package com.bhavnathacker.learnandroid.ui.codelabs

import com.bhavnathacker.learnandroid.utils.AppUtils

class CodelabRepository {
    fun getCodelabLevels(): List<CodelabLevel> {
        return AppUtils.getCodelabLevels()
    }
}
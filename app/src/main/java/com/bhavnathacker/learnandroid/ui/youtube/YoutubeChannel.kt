package com.bhavnathacker.learnandroid.ui.youtube

data class YoutubeChannel(
    val id: String,
    val name: String,
    val link: String,
    val image: String
) {

    val imageDrawable: String
        get() = "drawable/$image"
}

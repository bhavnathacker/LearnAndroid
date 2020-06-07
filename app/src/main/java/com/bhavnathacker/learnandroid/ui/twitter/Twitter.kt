package com.bhavnathacker.learnandroid.ui.twitter

data class Twitter(
    val id: String,
    val name: String,
    val link: String,
    val image: String
) {

    val imageDrawable: String
        get() = "drawable/$image"
}

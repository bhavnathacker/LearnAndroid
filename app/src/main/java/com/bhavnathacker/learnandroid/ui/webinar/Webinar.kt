package com.bhavnathacker.learnandroid.ui.webinar

data class Webinar(
    val id: String,
    val name: String,
    val duration: String,
    val link: String,
    val image: String
) {

    val imageDrawable: String
        get() = "drawable/$image"
}

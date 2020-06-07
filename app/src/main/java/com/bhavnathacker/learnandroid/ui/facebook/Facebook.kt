package com.bhavnathacker.learnandroid.ui.facebook

data class Facebook(
    val id: String,
    val name: String,
    val link: String,
    val image: String
) {

    val imageDrawable: String
        get() = "drawable/$image"
}

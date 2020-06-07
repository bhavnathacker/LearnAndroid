package com.bhavnathacker.learnandroid.ui.codelabs

data class CodelabLevel(
    val id: String,
    val name: String,
    val link: String,
    val image: String
) {

    val imageDrawable: String
        get() = "drawable/$image"
}

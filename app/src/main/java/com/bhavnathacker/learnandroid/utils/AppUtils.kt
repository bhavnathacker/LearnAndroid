package com.bhavnathacker.learnandroid.utils

import android.graphics.Color
import com.bhavnathacker.learnandroid.ui.codelabs.CodelabLevel
import com.bhavnathacker.learnandroid.ui.facebook.Facebook
import com.bhavnathacker.learnandroid.ui.twitter.Twitter
import com.bhavnathacker.learnandroid.ui.webinar.Webinar
import com.bhavnathacker.learnandroid.ui.youtube.YoutubeChannel
import java.util.*

object AppUtils {
    val progressItems =
        arrayOf(
            ProgressStatus.NOT_STARTED.status,
            ProgressStatus.IN_PROGRESS.status,
            ProgressStatus.COMPLETED.status
        )

    val progressDefault = progressItems[0]

    val subscribeItems =
        arrayOf(
            SubscribeStatus.NOT_SUBSCRIBED.status,
            SubscribeStatus.SUBSCRIBED.status
        )

    val subscribeDefault = subscribeItems[0]

    val followItems =
        arrayOf(
            FollowStatus.NOT_FOLLOWED.status,
            FollowStatus.FOLLOWED.status
        )

    val followDefault = followItems[0]

    fun getRandomColor(): Int {
        val rnd = Random()
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
    }

    fun getCodelabLevels(): List<CodelabLevel> {
        return listOf(
            CodelabLevel(
                "cl_1",
                "Beginner",
                "https://developer.android.com/courses/kotlin-bootcamp/overview",
                "beginner"
            ),
            CodelabLevel(
                "cl_2",
                "Intermediate",
                "https://developer.android.com/courses/kotlin-android-fundamentals/overview",
                "intermediate"
            ),
            CodelabLevel(
                "cl_3",
                "Advanced",
                "https://developer.android.com/courses/kotlin-android-advanced/overview",
                "advanced"
            )
        )
    }

    fun getWebinars(): List<Webinar> {
        return listOf(
            Webinar(
                "wb_1",
                "Kotlin 101",
                "60 mins",
                "https://eventsonair.withgoogle.com/events/kotlin?talk=kotlin101",
                "beginner"
            ),
            Webinar(
                "wb_2",
                "Decoding Kotlin: The Modern Way To Build on Android",
                "60 mins",
                "https://eventsonair.withgoogle.com/events/kotlin?talk=decodingkotlin",
                "intermediate"
            ),
            Webinar(
                "wb_3",
                "Live Coding an App with Kotlin",
                "60 mins",
                "https://eventsonair.withgoogle.com/events/kotlin?talk=live-coding",
                "advanced"
            )
        )
    }

    fun getYoutubeChannels(): List<YoutubeChannel> {
        return listOf(
            YoutubeChannel(
                "yt_1",
                "Android Developers",
                "https://www.youtube.com/user/androiddevelopers",
                "beginner"
            ),
            YoutubeChannel(
                "yt_2",
                "Firebase",
                "https://www.youtube.com/user/Firebase",
                "intermediate"
            ),
            YoutubeChannel(
                "yt_3",
                "Google Developers",
                "https://www.youtube.com/user/GoogleDevelopers",
                "advanced"
            )
        )
    }

    fun getFacebookEntries(): List<Facebook> {
        return listOf(
            Facebook(
                "fb_1",
                "Android Developers Facebook Page",
                "https://www.facebook.com/AndroidOfficial/",
                "beginner"
            ),
            Facebook(
                "fb_2",
                "Android Developers Facebook Group",
                "https://www.facebook.com/groups/AndroidDevelopers4",
                "intermediate"
            )
        )
    }

    fun getTwitterEntries(): List<Twitter> {
        return listOf(
            Twitter(
                "tw_1",
                "Android Developers",
                "https://twitter.com/AndroidDev",
                "beginner"
            ),
            Twitter(
                "tw_2",
                "Firebase",
                "https://twitter.com/Firebase",
                "intermediate"
            ),
            Twitter(
                "tw_3",
                "Google Developers",
                "https://twitter.com/googledevs",
                "advanced"
            ),
            Twitter(
                "tw_4",
                "Google Devs India",
                "https://twitter.com/GoogleDevsIN",
                "beginner"
            ),
            Twitter(
                "tw_5",
                "Google Developer Groups",
                "https://twitter.com/gdg",
                "intermediate"
            )
        )
    }


}
package com.learning.lessons.data.api.webinar

import com.learning.lessons.domain.entities.common.Timecode

data class ApiWebinar(
    val calendar_url: String = "",
    val categories: List<String> = listOf(),
    val description: String = "",
    val difficultyID: Int = 0,
    val dislikes: Int = 0,
    val duration: String = "",
    val id: Int = 0,
    val active: Boolean= false,
    val language: String = "",
    val likes: Int = 0,
    val promo_image_url: String = "",
    val region: String = "",
    val sort_order: Int = 0,
    val speaker_name: String = "",
    val tags: List<String> = listOf(),
    val timecodes: List<Timecode> = listOf(),
    val title: String = "",
    val video_url: String = "",
    val webinar_date: String = ""
)
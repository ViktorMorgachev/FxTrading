package com.learning.lessons.domain.entities.common

data class Timecode(
    val time: String,
    val title: String,
    var timeSeconds: Int
)
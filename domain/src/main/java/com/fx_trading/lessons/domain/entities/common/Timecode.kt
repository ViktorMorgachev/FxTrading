package com.fx_trading.lessons.domain.entities.common

data class Timecode(
    val is_active: Boolean,
    val time_length: String,
    val time_seconds: String,
    val title: String
)
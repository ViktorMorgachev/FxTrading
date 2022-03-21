package com.fx_trading.lessons.domain.entities.common

data class Timecode(
    val is_active: Boolean,
    val time: String,
    val timeSeconds: Long,
    val title: String
)
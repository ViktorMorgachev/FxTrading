package com.fx_trading.lessons.data.api.common

data class ApiTimeCode(
    val active: Boolean = false,
    val time: String = "",
    val time_seconds: Long = 0,
    val title: String = ""
)
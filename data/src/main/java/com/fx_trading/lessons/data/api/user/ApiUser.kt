package com.fx_trading.lessons.data.api.user

data class ApiUser(
    val date_created: Int,
    val date_logined: Int,
    val email: String,
    val is_active: Boolean,
    val language: String,
    val name: String,
    val rank: String,
    val region: String,
    val user_id: Int,
    val utm: String
)
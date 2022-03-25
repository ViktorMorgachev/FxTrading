package com.fx_trading.lessons.data.api.user

data class ApiUser(
    val date_created: String = "",
    val date_logined: String = "",
    val email: String = "",
    val language: String = "",
    val name: String = "",
    val rank: Int = 0,
    val region: String = "",
    val user_id: Int = -1,
    val utm: String = ""
)
package com.learning.lessons.data.api.user

import kotlinx.datetime.Clock

data class ApiUser(
    val date_created: String = "${Clock.System.now()}",
    val date_logined: String = "${Clock.System.now()}",
    val email: String = "",
    val language: String = "",
    val name: String = "",
    val rank: Int = 0,
    val region: String = "",
    val user_id: Int = -1,
    val utm: String = ""
)
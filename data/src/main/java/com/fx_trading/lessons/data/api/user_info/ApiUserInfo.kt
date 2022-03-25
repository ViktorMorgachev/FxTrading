package com.fx_trading.lessons.data.api.user_info

data class ApiUserInfo(val questions_ids: List<Int> = listOf(),
                       val likes_lessons_ids: List<Int> = listOf(),
                       val likes_webinars_ids: List<Int> = listOf(),
                       val dislikes_webinars_ids: List<Int> = listOf(),
                       val  user_id: Int = 0,
                       val dislikes_lessons_ids: List<Int> = listOf(),
                       val passed_lessons_ids: List<Int> = listOf())

package com.fx_trading.lessons.data.api.user_info

data class ApiUserInfo(val questions_ids: List<Long> = listOf(),
                       val likes_lessons_ids: List<Long> = listOf(),
                       val  user_id: Long = 0,
                       val dislikes_lessons_ids: List<Long> = listOf(),
                       val passed_lessons_ids: List<Long> = listOf())

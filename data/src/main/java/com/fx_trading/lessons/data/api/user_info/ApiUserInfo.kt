package com.fx_trading.lessons.data.api.user_info

data class ApiUserInfo(val quiz_results: List<ApiQuizResults> = listOf(), val likes_info_lessons_ids: List<Long> = listOf(), val  user_id: Long = 0, val dislikes_info_lessons_ids: List<Long> = listOf())

package com.fx_trading.lessons.data.api.user_info.mock

import com.fx_trading.lessons.data.api.user_info.ApiQuizResults
import com.fx_trading.lessons.data.api.user_info.ApiUserInfo

class MockData {
    companion object{
        val apiQuizResults = ApiQuizResults(quiz_group_id = 1, status = "Пройдён")
        val mockApiUserInfo = ApiUserInfo(user_id = 1, quiz_results = listOf(apiQuizResults), likes_info_lessons_ids = listOf(1, 2))
    }
}
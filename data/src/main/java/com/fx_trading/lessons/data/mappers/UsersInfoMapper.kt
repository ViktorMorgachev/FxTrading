package com.fx_trading.lessons.data.mappers

import com.fx_trading.lessons.data.api.user_info.ApiQuizResults
import com.fx_trading.lessons.data.api.user_info.ApiUserInfo
import com.fx_trading.lessons.domain.entities.users_info.QuizResults
import com.fx_trading.lessons.domain.entities.users_info.UserInfo


fun ApiUserInfo.toUserInfo(): UserInfo {
    return UserInfo(
        questionsIDs = questions_ids,
        likesLessons = likes_lessons_ids,
        user_id = user_id,
        dislikesLessons = dislikes_lessons_ids,
        passedLessons = passed_lessons_ids
    )
}
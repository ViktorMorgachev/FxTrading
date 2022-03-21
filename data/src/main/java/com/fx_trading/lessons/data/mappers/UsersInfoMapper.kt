package com.fx_trading.lessons.data.mappers

import com.fx_trading.lessons.data.api.user_info.ApiQuizResults
import com.fx_trading.lessons.data.api.user_info.ApiUserInfo
import com.fx_trading.lessons.domain.entities.users_info.QuizResults
import com.fx_trading.lessons.domain.entities.users_info.UserInfo


fun ApiUserInfo.toUserInfo(): UserInfo{
    return UserInfo(quiz_results = this.quiz_results.map { it.toQuizResults() }, likesLessons = likes_info_lessons_ids, userID = user_id, dislikesLessons = dislikes_info_lessons_ids)
}

fun ApiQuizResults.toQuizResults(): QuizResults{
    return QuizResults(quiz_group_id, status)
}

fun UserInfo.toApiUserInfo(): ApiUserInfo{
    return ApiUserInfo(quiz_results = this.quiz_results.map { it.toApiQuizResults() }, likesLessons, user_id = userID, dislikesLessons)
}

fun QuizResults.toApiQuizResults(): ApiQuizResults{
    return ApiQuizResults(quiz_group_id, status)
}
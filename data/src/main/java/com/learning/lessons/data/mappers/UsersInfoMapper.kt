package com.learning.lessons.data.mappers

import com.learning.lessons.data.api.user_info.ApiUserInfo
import com.learning.lessons.domain.entities.users_info.UserInfo


fun ApiUserInfo.toUserInfo(): UserInfo {
    return UserInfo(
        questionsIDs = questions_ids,
        likesLessons = likes_lessons_ids,
        user_id = user_id,
        dislikesLessons = dislikes_lessons_ids,
        passedLessons = passed_lessons_ids,
        dislikesWebinars = dislikes_webinars_ids,
        likesWebinars =  likes_webinars_ids
    )
}

fun UserInfo.toApiUserInfo(): ApiUserInfo {
    return ApiUserInfo(
        questions_ids =  questionsIDs,
        likes_lessons_ids = likesLessons,
        user_id = user_id,
        dislikes_lessons_ids = dislikesLessons,
        passed_lessons_ids = passedLessons,
        dislikes_webinars_ids = dislikesWebinars,
        likes_webinars_ids = likesWebinars
    )
}
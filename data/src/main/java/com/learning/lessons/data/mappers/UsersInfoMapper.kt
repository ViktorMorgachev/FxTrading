package com.learning.lessons.data.mappers

import com.learning.lessons.data.api.user_info.ApiUserInfo
import com.learning.lessons.domain.entities.users_info.UserInfo


fun ApiUserInfo.toUserInfo(): UserInfo {
    return UserInfo(
        questionsIDs = passed_questions_ids,
        likesLessons = likes_lessons_ids,
        user_id = user_id,
        dislikesLessons = dislikes_lessons_ids,
        passedLessons = passed_lessons_ids,
        dislikesWebinars = dislikes_webinars_ids,
        likesWebinars = likes_webinars_ids,
        devices_ids = devices_ids,
        dislikesCourses = dislikes_courses_ids,
        likesCourses = likes_courses_ids,
        passedCourses = passed_courses_ids,
        passedArticles = passed_articles_ids,
        readedArticles = readed_articles_ids
    )
}

fun UserInfo.toApiUserInfo(): ApiUserInfo {
    return ApiUserInfo(
        passed_questions_ids = questionsIDs,
        likes_lessons_ids = likesLessons,
        user_id = user_id,
        dislikes_lessons_ids = dislikesLessons,
        passed_lessons_ids = passedLessons,
        dislikes_webinars_ids = dislikesWebinars,
        likes_webinars_ids = likesWebinars,
        devices_ids = devices_ids,
        dislikes_courses_ids = dislikesCourses,
        likes_courses_ids = likesCourses,
        passed_courses_ids = passedCourses,
        passed_articles_ids = passedArticles,
        readed_articles_ids = readedArticles
    )
}
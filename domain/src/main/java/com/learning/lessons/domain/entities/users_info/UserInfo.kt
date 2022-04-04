package com.learning.lessons.domain.entities.users_info

data class UserInfo(
    val questionsIDs: List<Int>,
    val likesLessons: List<Int>,
    val likesWebinars: List<Int>,
    val likesCourses:  List<Int>,
    val dislikesCourses:  List<Int>,
    val dislikesWebinars: List<Int>,
    val user_id: Int = 0,
    val dislikesLessons: List<Int>,
    val passedLessons: List<Int>,
    val passedCourses: List<Int>,
    val devices_ids: List<String>,
    val passedArticles: List<Int>,
    val readedArticles: List<Int>,
)

enum class ApiUserInfoFields(val fieldName: String){
    PassedQuestionsIDs("passed_questions_ids"),
    LikesCourses("likes_courses_ids"),
    DislikesCourses("dislikes_courses_ids"),
    LikesWebinars("likes_webinars_ids"),
    DislikesWebinars("dislikes_webinars_ids"),
    LikesLessons("likes_lessons_ids"),
    DislikesLessons("dislikes_lessons_ids"),
    PassedLessons("passed_lessons_ids"),
    PassedCourses("passed_courses_ids"),
    DevicesIDs("devices_ids"),
    PassedArticles("passed_articles_ids"),
    ReadedArticles("readed_articles_ids")
}

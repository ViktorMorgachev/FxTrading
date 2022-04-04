package com.learning.lessons.domain.entities.course

data class Course(
    val description: String,
    val dislikes: Int,
    val id: Int,
    val is_active: Boolean,
    val language: String,
    val lessons_id: List<Int>,
    val likes: Int,
    val difficulty: Int,
    val title: String,
    val promo_image_url: String,
    val region: String
)

enum class ApiCourseFields(val fieldName: String){
    Likes("likes"),
    Description("description"),
    Dislikes("dislikes"),
    LessonsIDS("lessons_ids")
}
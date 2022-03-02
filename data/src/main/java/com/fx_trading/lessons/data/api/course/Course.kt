package com.fx_trading.lessons.data.api.course

data class Course(
    val description: String,
    val dislikes: Int,
    val end_date: String,
    val id: Int,
    val is_active: Boolean,
    val language: String,
    val lessons_id: List<String>,
    val likes: Int,
    val name: String,
    val promo_image_url: String,
    val quiz_group_id: String,
    val region: String,
    val sort_order: Int,
    val start_date: String
)
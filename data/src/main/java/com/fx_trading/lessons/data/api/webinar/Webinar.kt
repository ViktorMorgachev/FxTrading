package com.fx_trading.lessons.data.api.webinar

import com.fx_trading.lessons.data.api.common.ApiTimeCode

data class Webinar(
    val calendar_url: String,
    val categories: List<Category>,
    val description: String,
    val difficulty: String,
    val dislikes: Int,
    val duration: String,
    val id: Int,
    val is_active: Boolean,
    val language: String,
    val likes: Int,
    val promo_image_url: String,
    val region: String,
    val sort_order: Int,
    val speaker_name: String,
    val tags: List<String>,
    val apiTimeCodes: List<ApiTimeCode>,
    val title: String,
    val video_url: String,
    val webinar_date: String
)
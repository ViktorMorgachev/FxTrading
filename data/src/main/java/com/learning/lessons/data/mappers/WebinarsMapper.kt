package com.learning.lessons.data.mappers

import com.learning.lessons.data.api.webinar.ApiWebinar
import com.learning.lessons.domain.entities.webinar.Webinar

fun ApiWebinar.toWebinar(): Webinar{
    return Webinar(calendar_url, categories, description, difficulty, dislikes, duration, id, active, language, likes, promo_image_url, region, sort_order, speaker_name, tags, timecodes, title, video_url, webinar_date)
}

fun Webinar.toApiWebinar(): ApiWebinar{
    return ApiWebinar(calendar_url, categories, description, difficultyID, dislikes, duration, id, is_active, language, likes, promo_image_url, region, sort_order, speaker_name, tags, timecodes, title, video_url, webinar_date)
}
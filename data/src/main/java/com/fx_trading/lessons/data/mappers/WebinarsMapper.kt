package com.fx_trading.lessons.data.mappers

import com.fx_trading.lessons.data.api.webinar.ApiWebinar
import com.fx_trading.lessons.domain.entities.webinar.Webinar

fun ApiWebinar.toWebinar(): Webinar{
    return Webinar(calendar_url, categories, description, difficulty, dislikes, duration, id, is_active, language, likes, promo_image_url, region, sort_order, speaker_name, tags, timecodes, title, video_url, webinar_date)
}
package com.fx_trading.lessons.data.mappers

import com.fx_trading.lessons.domain.entities.common.Timecode
import com.fx_trading.lessons.data.api.common.ApiTimeCode
import com.fx_trading.lessons.domain.entities.lesson.Category
import com.fx_trading.lessons.domain.entities.lesson.Comment
import com.fx_trading.lessons.data.api.lesson.ApiCategory
import com.fx_trading.lessons.domain.entities.lesson.Lesson
import com.fx_trading.lessons.domain.entities.lesson.Questions
import com.fx_trading.lessons.data.api.lesson.ApiQuizGroup
import com.fx_trading.lessons.data.api.lesson.ApiLesson
import com.fx_trading.lessons.data.api.lesson.ApiComment


fun ApiLesson.toLesson(): Lesson{
    return Lesson(categories = this.categories.map { it.toCategory() },
        comments = this.apiComments.map { it.toComment() },
        description = this.description,
        difficulty = this.difficulty,
        dislikes = this.dislikes,
        duration = this.duration,
        id = this.id,
        is_active = this.is_active,
        language = this.language,
        likes = this.likes,
        promo_image_url = this.promo_image_url,
        questions = this.quiz_group.map { it.toQuizGroup() },
        region = this.region,
        sort_order = this.sort_order,
        speaker_name = this.speaker_name,
        tags = this.tags,
        text_version_link = this.text_version_link,
        timecodes = this.timeCodes.map { it.toTimeCode() },
        title = this.title,
        video_url = this.video_url,
    )
}

fun ApiCategory.toCategory() : Category = Category(name = this.name)
fun ApiComment.toComment(): Comment =  Comment(id = this.id)
fun ApiQuizGroup.toQuizGroup(): Questions = Questions(quiz_id = this.quiz_id)
fun ApiTimeCode.toTimeCode(): Timecode {
    return Timecode(is_active = this.is_active, time_length = this.time_length, time_seconds = this.time_seconds, title = this.title)
}
package com.learning.lessons.data.mappers

import com.learning.lessons.domain.entities.common.Timecode
import com.learning.lessons.data.api.common.ApiTimeCode
import com.learning.lessons.domain.entities.lesson.Comment
import com.learning.lessons.domain.entities.lesson.Lesson
import com.learning.lessons.domain.entities.lesson.Questions
import com.learning.lessons.data.api.lesson.ApiQuestionGroupID
import com.learning.lessons.data.api.lesson.ApiLesson
import com.learning.lessons.data.api.lesson.ApiComment
import com.learning.lessons.utils.utils.toSeconds


fun ApiLesson.toLesson(): Lesson{
    return Lesson(categories = this.categories,
        comments = this.comments.map { it.toComment() },
        description = this.description,
        difficulty = this.difficultyID,
        dislikes = this.dislikes,
        duration = this.duration,
        id = this.id,
        is_active = this.active,
        language = this.language,
        likes = this.likes,
        promo_image_url = this.promo_image_url,
        question_group = this.questions_group,
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
fun ApiComment.toComment(): Comment =  Comment(id = this.id)
fun ApiQuestionGroupID.toQuestionGroupID(): Questions = Questions(quiz_id = this.id)

fun ApiTimeCode.toTimeCode(): Timecode {
    val title = time_title.substringAfterLast(" ")
    val time = time_title.substringBefore(" ")
    return Timecode(is_active = active, time = time, title = title, timeSeconds = time.toSeconds())
}
package com.learning.lessons.data.mappers

import com.learning.lessons.data.api.course.ApiCourse
import com.learning.lessons.domain.entities.course.Course


fun ApiCourse.toCourse(): Course{
    return Course(description = description, dislikes, id, active, language, lessons_ids, likes, difficulty, title,promo_image_url, region)
}
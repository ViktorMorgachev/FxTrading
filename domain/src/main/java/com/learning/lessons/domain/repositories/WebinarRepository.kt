package com.learning.lessons.domain.repositories

import com.learning.lessons.domain.entities.webinar.Webinar

interface WebinarRepository: FieldsUpdateable {
    suspend fun getWebinars(): List<Webinar>
    suspend fun getWebinarByID(id: Int): Webinar?

}
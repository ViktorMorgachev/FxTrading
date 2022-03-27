package com.learning.lessons.domain.repositories

import com.learning.lessons.domain.entities.webinar.Webinar

interface WebinarRepository {
    suspend fun getWebinars(): List<Webinar>
    suspend fun getWebinarByID(id: Int): Webinar?
    suspend fun getWebinarForceByID(id: Int): Webinar?
    suspend fun updateWebinar(webinar: Webinar): Boolean
}
package com.learning.lessons.domain.repositories

import com.learning.lessons.domain.entities.webinar.Webinar
import kotlinx.coroutines.Deferred

interface WebinarRepository {
    suspend fun getWebinars(): List<Webinar>
    suspend fun getWebinarByID(id: Int): Webinar?
    suspend fun updateWebinarField(webinarID: Int, fieldValue: Any, field: String): Deferred<Boolean>

}
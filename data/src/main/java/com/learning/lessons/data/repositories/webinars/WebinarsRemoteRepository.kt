package com.learning.lessons.data.repositories.webinars

import com.learning.lessons.data.api.webinar.ApiWebinar

interface WebinarsRemoteRepository {
    suspend fun getWebinars(): List<ApiWebinar>
    suspend fun getWebinarByID(id: Int): ApiWebinar?
    suspend fun updateWebinar(webinar: ApiWebinar): Boolean
    suspend fun updateWebinarField(webinarID: Int, fieldValue: Any, field: String)
}
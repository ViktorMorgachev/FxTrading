package com.learning.lessons.data.repositories.webinars

import com.learning.lessons.data.api.webinar.ApiWebinar
import com.learning.lessons.data.repositories.FieldsUpdateable
import kotlinx.coroutines.flow.Flow

interface WebinarsRemoteRepository: FieldsUpdateable {
    suspend fun getWebinars(): List<ApiWebinar>
    suspend fun getWebinarByID(id: Int): ApiWebinar?
}
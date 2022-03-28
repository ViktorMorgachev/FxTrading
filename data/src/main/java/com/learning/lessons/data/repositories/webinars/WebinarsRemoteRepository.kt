package com.learning.lessons.data.repositories.webinars

import com.learning.lessons.data.api.webinar.ApiWebinar
import kotlinx.coroutines.flow.Flow

interface WebinarsRemoteRepository {
    suspend fun getWebinars(): List<ApiWebinar>
    suspend fun getWebinarsFlow(): Flow<List<ApiWebinar>>
    suspend fun getWebinarByID(id: Int): ApiWebinar?
    suspend fun getWebinarByIDFlow(id: Int): Flow<ApiWebinar?>
    suspend fun updateWebinarField(webinarID: Int, fieldValue: Any, field: String): Flow<Boolean>
}
package com.learning.lessons.data.repositories.webinars

import com.learning.lessons.data.api.webinar.ApiWebinar
import com.learning.lessons.data.repositories.AutoUpdatable
import com.learning.lessons.data.repositories.FieldsUpdateable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

interface WebinarsRemoteRepository: FieldsUpdateable, AutoUpdatable {
    suspend fun getWebinars(): List<ApiWebinar>
    suspend fun getWebinarByID(id: Int): ApiWebinar?
}
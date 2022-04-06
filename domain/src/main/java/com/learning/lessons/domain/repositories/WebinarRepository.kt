package com.learning.lessons.domain.repositories

import com.learning.lessons.domain.entities.webinar.Webinar
import kotlinx.coroutines.flow.MutableStateFlow

interface WebinarRepository: FieldsUpdateable {
    suspend fun getWebinars(): List<Webinar>
    suspend fun getWebinarByID(id: Int): Webinar?
    suspend fun getWebinarsFlow(): MutableStateFlow<List<Webinar>>
}
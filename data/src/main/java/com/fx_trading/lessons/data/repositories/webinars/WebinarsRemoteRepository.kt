package com.fx_trading.lessons.data.repositories.webinars

import com.fx_trading.lessons.data.api.webinar.ApiWebinar

interface WebinarsRemoteRepository {
    suspend fun getWebinars(): List<ApiWebinar>
    suspend fun getWebinarByID(id: Long): ApiWebinar?
    suspend fun updateWebinar(webinar: ApiWebinar)
}
package com.fx_trading.lessons.domain.repositories

import com.fx_trading.lessons.domain.entities.webinar.Webinar

interface WebinarRepository {
    suspend fun getWebinars(): List<Webinar>
    suspend fun getWebinarByID(id: Int): Webinar?
    suspend fun updateWebinar(webinarID: Int)
}
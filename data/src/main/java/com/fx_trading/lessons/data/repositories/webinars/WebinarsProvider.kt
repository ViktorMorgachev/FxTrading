package com.fx_trading.lessons.data.repositories.webinars

import com.fx_trading.lessons.data.mappers.toWebinar
import com.fx_trading.lessons.data.store.WebinarsDataSource
import com.fx_trading.lessons.domain.entities.webinar.Webinar
import com.fx_trading.lessons.domain.repositories.WebinarRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WebinarsProvider  @Inject constructor(private val webinarsDataSource: WebinarsDataSource): WebinarRepository {

    override suspend fun getWebinars(): List<Webinar> {
       return webinarsDataSource.getWebinars().map { it.toWebinar() }
    }

    override suspend fun getWebinarByID(id: Int): Webinar? {
        return webinarsDataSource.getWebinarByID(id)?.toWebinar()
    }

    override suspend fun updateWebinar(webinarID: Int) {
        return webinarsDataSource.updateWebinar(webinarID)
    }
}
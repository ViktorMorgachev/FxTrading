package com.fx_trading.lessons.data.store

import com.fx_trading.lessons.data.api.webinar.ApiWebinar
import com.fx_trading.lessons.data.repositories.webinars.WebinarsRemoteRepository
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class WebinarsDataSource @Inject constructor(val webinarsRemoteRepository: WebinarsRemoteRepository) {

    companion object{
        var cache: List<ApiWebinar> = mutableListOf()
    }

     suspend fun getWebinars(): List<ApiWebinar> {
         return if (cache.isNotEmpty()) cache else {
             cache = webinarsRemoteRepository.getWebinars()
             cache
         }
    }

     suspend fun getWebinarByID(id: Int): ApiWebinar? {
         return if (cache.isNotEmpty()) cache.first { it.id == id } else {
             cache = webinarsRemoteRepository.getWebinars()
             cache.first { it.id == id }
         }
    }

     suspend fun updateWebinar(webinarID: Int) {
        val webinar = getWebinarByID(webinarID)
         webinar?.let {
             webinarsRemoteRepository.updateWebinar(webinar)
         }
    }
}
package com.learning.lessons.data.store

import com.learning.lessons.data.api.webinar.ApiWebinar
import com.learning.lessons.data.mappers.toApiWebinar
import com.learning.lessons.data.repositories.webinars.WebinarsRemoteRepository
import com.learning.lessons.domain.entities.webinar.Webinar
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WebinarsDataSource @Inject constructor(val webinarsRemoteRepository: WebinarsRemoteRepository) {

    companion object {
        var cache: MutableList<ApiWebinar> = mutableListOf()
    }

    suspend fun getWebinars(): List<ApiWebinar> {
        return if (cache.isNotEmpty()) cache else {
            cache = webinarsRemoteRepository.getWebinars().toMutableList()
            cache
        }
    }

    suspend fun getWebinarByID(id: Int, forceUpdate: Boolean = false): ApiWebinar? {
        if (forceUpdate) {
            val webinars = getWebinars()
            cache = webinars.toMutableList()
            return cache.firstOrNull { it.id == id }
        } else
            return if (cache.isNotEmpty()) cache.firstOrNull { it.id == id } else {
                return webinarsRemoteRepository.getWebinarByID(id)
            }
    }

    suspend fun updateWebinar(webinar: Webinar): Boolean {
        val success = webinarsRemoteRepository.updateWebinar(webinar.toApiWebinar())
        if (success){
            cache.remove(cache.first { it.id == webinar.id })
            cache.add(webinar.toApiWebinar())
            return true
        } else {
            return false
        }
    }
}
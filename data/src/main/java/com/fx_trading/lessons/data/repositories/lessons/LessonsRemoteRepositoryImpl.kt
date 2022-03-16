package com.fx_trading.lessons.data.repositories.lessons

import com.fx_trading.lessons.data.api.lesson.ApiLesson
import com.fx_trading.lessons.utils.utils.Logger
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class LessonsRemoteRepositoryImpl @Inject constructor(): LessonsRemoteRepository {

    @Inject
    lateinit var firebaseFireStore: FirebaseFirestore

    override suspend fun getRemoteLessons(): List<ApiLesson> {
        return emptyList()
    }

    override suspend fun getRemoteLessonByID(id: Int): ApiLesson {
        TODO("Not yet implemented")
    }
}
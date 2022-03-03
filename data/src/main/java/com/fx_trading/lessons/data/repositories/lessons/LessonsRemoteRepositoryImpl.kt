package com.fx_trading.lessons.data.repositories.lessons

import com.fx_trading.lessons.data.api.lesson.ApiLesson
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class LessonsRemoteRepositoryImpl: LessonsRemoteRepository {

    @Inject
    lateinit var firebaseFireStore: FirebaseFirestore

    override suspend fun getRemoteLessons(): List<ApiLesson> {
        TODO("Not yet implemented")
    }

    override suspend fun getRemoteLessonByID(): ApiLesson {
        TODO("Not yet implemented")
    }
}
package com.learning.lessons.data.repositories.lessons

import com.learning.lessons.data.api.lesson.ApiLesson
import com.learning.lessons.data.extentions.await
import com.learning.lessons.utils.utils.Logger
import com.google.firebase.firestore.FirebaseFirestore
import com.learning.lessons.data.BuildConfig
import com.learning.lessons.data.extentions.toObjectOrDefault
import com.learning.lessons.data.repositories.FieldUpdateableRealisation
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LessonsRemoteRepositoryImpl @Inject constructor(private val firebaseFirestore: FirebaseFirestore, private val fieldUpdateableRealisation: FieldUpdateableRealisation
): LessonsRemoteRepository {

    private val logger_tag = this::class.java.simpleName
    private val documentPath = "${BuildConfig.DOCUMENT_DB_PATH}Lessons"

    init {
        fieldUpdateableRealisation.updateFieldDocumentPath = documentPath
    }

    override suspend fun getRemoteLessons(): List<ApiLesson> {
        return try {
            val firebaseDocuments =  firebaseFirestore.collection(documentPath).get().await()
            firebaseDocuments?.mapNotNull { it.toObjectOrDefault(ApiLesson::class.java) }?.filter { it.active } ?: listOf()
        } catch (e: Exception) {
            Logger.log(logger_tag, exception = e)
            listOf()
        }
    }

    override suspend fun getRemoteLessonByID(id: Int): ApiLesson? {
        return try {
            val firebaseDocument = firebaseFirestore.collection(documentPath).document("$id").get().await()
            firebaseDocument?.toObjectOrDefault(ApiLesson::class.java)
        } catch (e: Exception) {
            Logger.log(logger_tag, exception = e)
            null
        }
    }

    override suspend fun updateFields(
        objectID: Int,
        fieldValues: List<Pair<String, Any>>
    ): Flow<Boolean> {
     return  fieldUpdateableRealisation.updateFields(objectID, fieldValues)
    }



}
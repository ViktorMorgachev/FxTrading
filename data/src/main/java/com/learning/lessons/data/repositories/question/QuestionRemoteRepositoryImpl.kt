package com.learning.lessons.data.repositories.question

import com.learning.lessons.data.BuildConfig
import com.learning.lessons.data.api.question_group.ApiQuestionGroup
import com.learning.lessons.data.extentions.await
import com.learning.lessons.utils.utils.Logger
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class QuestionRemoteRepositoryImpl @Inject constructor(var firebaseFireStore: FirebaseFirestore) : QuestionRemoteRepository {

    private val documentPath = BuildConfig.DOCUMENT_DB_PATH

    override suspend fun getRemoteQuestionGroups(): List<ApiQuestionGroup> {
        try {
            val firebaseDocuments =  firebaseFireStore.collection("${documentPath}Exams").get().await()
            if (firebaseDocuments != null && !firebaseDocuments.isEmpty) {
                val apiQuestionGroups = firebaseDocuments.documents.mapNotNull {
                    try {
                        it.toObject(ApiQuestionGroup::class.java)
                    } catch (e: Exception){
                        Logger.log("getRemoteQuestionGroups", ".toObject(ApiLesson::class.java)", exception = e)
                        null
                    }
                }
                Logger.log("getRemoteQuestionGroups", "Data ${firebaseDocuments.documents}")
                return apiQuestionGroups
            } else {
                Logger.log("getRemoteQuestionGroups", "Error getting documents.")
            }
            return listOf()
        } catch (e: Exception) {
            Logger.log("getRemoteQuestionGroups", "Error getting documents.", exception = e)
            return listOf()
        }
    }

    override suspend fun getRemoteQuestionGroup(id: Int): ApiQuestionGroup? {
        return try {
            val firebaseDocument = firebaseFireStore.collection("${documentPath}Exams").document("$id").get().await()
            if (firebaseDocument != null && !firebaseDocument.data.isNullOrEmpty()) {
                firebaseDocument.toObject(ApiQuestionGroup::class.java)
            } else {
                Logger.log("getRemoteQuestionGroup", "Error getting documents.")
                null
            }
        } catch (e: Exception) {
            Logger.log("getRemoteQuestionGroup", "Error getting documents.", exception = e)
            null
        }
    }

    override suspend fun getStartQuestionGroup(): ApiQuestionGroup? {
        return try {
            val apiQuestionGroups = getRemoteQuestionGroups()
            apiQuestionGroups.firstOrNull { it.start_exam }
        } catch (e: Exception) {
            Logger.log("getStartQuestionGroup", "Error getting documents.", e)
            null
        }
    }
}


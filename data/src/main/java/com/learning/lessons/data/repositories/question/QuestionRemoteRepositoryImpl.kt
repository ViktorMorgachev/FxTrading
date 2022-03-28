package com.learning.lessons.data.repositories.question

import com.learning.lessons.data.BuildConfig
import com.learning.lessons.data.api.question_group.ApiQuestionGroup
import com.learning.lessons.data.extentions.await
import com.learning.lessons.utils.utils.Logger
import com.google.firebase.firestore.FirebaseFirestore
import com.learning.lessons.data.extentions.toObjectOrDefault
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuestionRemoteRepositoryImpl @Inject constructor(var firebaseFireStore: FirebaseFirestore) : QuestionRemoteRepository {

    private val logger_tag = this::class.java.simpleName
    private val documentPath = BuildConfig.DOCUMENT_DB_PATH

    override suspend fun getRemoteQuestionGroups(): List<ApiQuestionGroup> {
        return try {
            val firebaseDocuments =  firebaseFireStore.collection("${documentPath}Exams").get().await()
            firebaseDocuments?.mapNotNull { it.toObjectOrDefault(ApiQuestionGroup::class.java) } ?: listOf()
        } catch (e: Exception) {
            Logger.log(logger_tag, exception = e)
            listOf()
        }
    }

    override suspend fun getRemoteQuestionGroup(id: Int): ApiQuestionGroup? {
        return try {
            val firebaseDocument = firebaseFireStore.collection("${documentPath}Exams").document("$id").get().await()
            firebaseDocument?.toObjectOrDefault(ApiQuestionGroup::class.java)
        } catch (e: Exception) {
            Logger.log(logger_tag, exception = e)
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


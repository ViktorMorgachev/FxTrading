package com.learning.lessons.data.repositories.articles

import com.google.firebase.firestore.FirebaseFirestore
import com.learning.lessons.data.BuildConfig
import com.learning.lessons.data.api.article.ApiArticle
import com.learning.lessons.data.extentions.await
import com.learning.lessons.data.extentions.toObjectOrDefault
import com.learning.lessons.utils.utils.Logger
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleRemoteRepositoryImpl @Inject constructor(private val firebaseFirestore: FirebaseFirestore): ArticleRemoteRepository {

    private val logger_tag = this::class.java.simpleName
    private val documentPath = "${BuildConfig.DOCUMENT_DB_PATH}Articles"

    override suspend fun getRemoteArticles(): List<ApiArticle> {
        return try {
            val firebaseDocuments = firebaseFirestore.collection(documentPath).get().await()
            firebaseDocuments?.documents?.mapNotNull { it.toObjectOrDefault(ApiArticle::class.java) }?: listOf()
        } catch (e : Exception){
            Logger.log(logger_tag,  exception = e)
            listOf()
        }
    }

    override suspend fun getRemoteArticleByID(articleID: Int): ApiArticle? {
        return try {
            val firebaseDocument = firebaseFirestore.collection(documentPath).document("$articleID").get().await()
            firebaseDocument?.toObjectOrDefault(ApiArticle::class.java)
        } catch (e: Exception) {
            Logger.log(logger_tag, exception = e)
            null
        }
    }
}
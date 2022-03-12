package com.fx_trading.lessons.data.repositories.question

import com.fx_trading.lessons.data.BuildConfig
import com.fx_trading.lessons.data.api.question_group.ApiAnswer
import com.fx_trading.lessons.data.api.question_group.ApiQuestion
import com.fx_trading.lessons.data.api.question_group.ApiQuestionGroup
import com.fx_trading.lessons.data.api.question_group.mock.MockData.Companion.apiAnswerDefault
import com.fx_trading.lessons.data.api.question_group.mock.MockData.Companion.apiQuestionDefault
import com.fx_trading.lessons.data.api.question_group.mock.MockData.Companion.apiQuestionGroup
import com.fx_trading.lessons.data.api.question_group.mock.MockData.Companion.apiQuestionGroupDefault
import com.fx_trading.lessons.data.extentions.await
import com.fx_trading.lessons.data.extentions.getField
import com.fx_trading.lessons.utils.utils.Logger
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

val documentPath = BuildConfig.DOCUMENT_DB_PATH
val use_mock_data = BuildConfig.USE_MOCK_DATA

@Singleton
class QuestionRemoteRepositoryImpl @Inject constructor() : QuestionRemoteRepository {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    @Inject
    lateinit var firebaseFireStore: FirebaseFirestore

    override suspend fun getRemoteQuestionGroups() = flow<List<ApiQuestionGroup>> {

        firebaseFireStore.collection("exams").document(documentPath)
            .get()
            .addOnCompleteListener { task ->
                coroutineScope.launch {
                    if (task.isSuccessful) {
                        task.result?.let { result ->
                            Logger.log(
                                "QuestionRemoteRepository",
                                "Data ${result.id + " => " + result.data}"
                            )
                        }

                    } else {
                        Logger.log(
                            "QuestionRemoteRepository",
                            "Error getting documents.",
                            task.exception
                        )
                        coroutineScope.launch {
                            emit(emptyList())
                        }
                    }
                }

            }.addOnFailureListener {
                Logger.log("QuestionRemoteRepository", "Error getting documents.", it)
                coroutineScope.launch {
                    emit(emptyList())
                }
            }
    }

    override suspend fun getRemoteQuestionGroup(id: Int) = flow {
        CoroutineScope(Dispatchers.IO).launch {
            firebaseFireStore.collection("exams").document("/${id}")
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        task.result?.let { result ->
                            Logger.log(
                                "QuestionRemoteRepository",
                                "Data ${result.id + " => " + result.data}"
                            )
                        }
                        coroutineScope.launch {
                            if (use_mock_data)
                                emit(apiQuestionGroup)
                        }
                    } else {
                        Logger.log(
                            "QuestionRemoteRepository",
                            "Error getting documents.",
                            task.exception
                        )
                        coroutineScope.launch {
                            emit(null)
                        }
                    }
                }
        }
    }

    @ExperimentalCoroutinesApi
    override suspend fun getFirstExamRemoteQuestionGroup() = flow<ApiQuestionGroup?> {
        try {
            val result = firebaseFireStore.collection("exams").document(documentPath).get().await()
            if (result == null || result.data == null) {
                Logger.log("QuestionRemoteRepository", "Error getting documents.")
                coroutineScope.launch {
                    emit(null)
                }
                emit(null)
                return@flow
            } else {
                val firebaseQuestionGroup = result.data!!.entries
                val Result: ArrayList<ApiQuestionGroup> = arrayListOf()
                var data: ApiQuestionGroup = apiQuestionGroupDefault

                firebaseQuestionGroup.forEach {
                    Logger.log("getFirstExamRemoteQuestionGroup", "Data: $it")
                    val hashMap = it.value as HashMap<String, Any?>
                    val apiQuestions = ArrayList<ApiQuestion>()
                    // Получаем списое всех вопросов
                    hashMap.getField<ArrayList<*>>("questions", arrayListOf<String>()).forEach { questionsMap ->
                            val questionsHashMap = questionsMap as HashMap<String, Any?>
                            val questionAnswers = ArrayList<ApiAnswer>()
                            // Init answers list
                           // Получаем списое всех ответов
                            questionsHashMap.getField<ArrayList<*>>("answers", arrayListOf<String>()).forEach { answers->
                                val answerHashMap = answers as HashMap<String, Any?>
                                questionAnswers.add(
                                    apiAnswerDefault.copy(
                                        is_active = answerHashMap.getField("is_active", false),
                                        optional_image_url = answerHashMap.getField("optional_image_url", ""),
                                        text = answerHashMap.getField("text", ""),
                                        is_correct = answerHashMap.getField("is_correct", 0),
                                    )
                                )

                            }
                            apiQuestions.add(
                                apiQuestionDefault.copy(
                                    description = questionsHashMap.getField("description", ""),
                                    is_active = questionsHashMap.getField("is_active", false),
                                    optional_image_url = questionsHashMap.getField("optional_image_url", ""),
                                    difficulty = questionsHashMap.getField("difficulty", 0),
                                    title = questionsHashMap.getField("title", ""),
                                    apiAnswers = questionAnswers
                                )
                            )
                        }
                    data = data.copy(
                        is_active = hashMap.getField("is_active", false),
                        parent_id = hashMap.getField("parent_id", 0),
                        name = hashMap.getField("name", ""),
                        language = hashMap.getField("language", ""),
                        id = hashMap.getField("id", 0),
                        region = hashMap.getField("region", ""),
                        correct_for_success = hashMap.getField("correct_for_success", 0),
                        available_attempts = hashMap.getField("available_attempts", 0),
                        isStartExam = hashMap.containsKey("is_start_exam"),
                        apiQuestions = apiQuestions
                    )
                    Result.add(data)
                }
                Logger.log("getFirstExamRemoteQuestionGroup", "AllData: ${Result.map { it.toString() }}")
                if (use_mock_data) {
                    emit(apiQuestionGroup)
                } else {
                    emit(Result.firstOrNull { it.isStartExam })
                }
            }
        } catch (e: Exception) {
            Logger.log("QuestionRemoteRepository", "Error getting documents.", e)
            emit(apiQuestionGroup)
        }
    }
}


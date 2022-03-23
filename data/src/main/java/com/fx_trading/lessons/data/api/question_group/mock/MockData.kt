package com.fx_trading.lessons.data.api.question_group.mock

import com.fx_trading.lessons.data.api.question_group.ApiAnswer
import com.fx_trading.lessons.data.api.question_group.ApiQuestion
import com.fx_trading.lessons.data.api.question_group.ApiQuestionGroup

class MockData {
    companion object {
        val apiAnswerDefault =
            ApiAnswer(active = false, correct = 0, optional_image_url = "", text = "")
        val apiQuestionDefault = ApiQuestion(
            answers = listOf(),
            description = "",
            difficulty = 0,
            active = false,
            optional_image_url = "",
            title = ""
        )
        val apiQuestionGroupDefault = ApiQuestionGroup(
            available_attempts = 0,
            correct_for_success = 0,
            id = -1,
            active = false,
            language = "",
            name = "",
            parent_id = 0,
            questions = listOf(),
            region = "",
            start_exam = false
        )

        private val apiAnswers1 = listOf<ApiAnswer>(
            apiAnswerDefault.copy(text = "Австралия"),
            apiAnswerDefault.copy(text = "Африка"),
            apiAnswerDefault.copy(text = "Евразия", correct = 1),
            apiAnswerDefault.copy(text = "Северная Америка")
        )
        private val apiAnswers2 = listOf<ApiAnswer>(
            apiAnswerDefault.copy(text = "Кипение", correct = 1),
            apiAnswerDefault.copy(text = "Конденсация", correct = 1),
            apiAnswerDefault.copy(text = "Окисление"),
            apiAnswerDefault.copy(text = "Излучение", correct = 1)
        )
        private val apiQuestion = ApiQuestion(
            answers = apiAnswers1,
            description = "Вопрос связаный с материками",
            difficulty = 1,
            active = true,
            optional_image_url = "https://i.sunhome.ru/journal/67/skorost-sveta-v2.xxl.jpg",
            title = "Какой материк самый большой"
        )
        private val apiQuestion2 = ApiQuestion(
            answers = apiAnswers2,
            description = "Вопросы по физике",
            difficulty = 2,
            active = true,
            optional_image_url = "gs://fx-trading-lessons.appspot.com/images/skorost-sveta-v2.xxl.jpg",
            title = "Выберите что относится к физической реакции"
        )

        val apiQuestionGroup = ApiQuestionGroup(
            available_attempts = 0,
            correct_for_success = 2,
            id = 1,
            active = true,
            language = "RU",
            name = "Тестовая группа вопросов",
            parent_id = 0,
            questions = listOf(apiQuestion, apiQuestion2),
            region = "RU",
            start_exam = true
        )


    }
}

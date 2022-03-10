package com.fx_trading.lessons.data.api.question_group.mock

import com.fx_trading.lessons.data.api.question_group.ApiAnswer
import com.fx_trading.lessons.data.api.question_group.ApiQuestion
import com.fx_trading.lessons.data.api.question_group.ApiQuestionGroup

class MockData {
    companion object{
        val apiAnswer = ApiAnswer(is_active = true, is_correct = 0, optional_image_url = "", text = "Этот ответ верный")

        val apiQuestion  = ApiQuestion(apiAnswers = listOf(apiAnswer, apiAnswer.copy(text = "Или этот ответ верный?", is_correct = 1), apiAnswer.copy(text = "Или этот?", is_correct = 1)), description = "Description", difficulty = 1, is_active = true, optional_image_url = "", title = "Выбери правильный ответ")

        val apiQuestionGroup = ApiQuestionGroup(available_attempts = 0, correct_for_success = 1, id = 1, is_active = true, language = "RU", name = "Тестовая группа вопросов",
            parent_id = 0, apiQuestions = listOf(apiQuestion), region = "RU", isStartExam = true)

        val apiAnswerDefault = ApiAnswer(is_active = false, is_correct = 0, optional_image_url = "", text = "")
        val apiQuestionDefault  = ApiQuestion(apiAnswers = listOf(), description = "", difficulty = 0, is_active = false, optional_image_url = "", title = "")
        val apiQuestionGroupDefault  = ApiQuestionGroup(available_attempts = 0, correct_for_success = 0, id = -1, is_active = false, language = "", name = "", parent_id = 0, apiQuestions = listOf(), region = "", isStartExam = false)
    }
}

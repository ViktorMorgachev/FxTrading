package com.fx_trading.lessons.data.mappers


import com.fx_trading.lessons.data.api.question_group.ApiAnswer
import com.fx_trading.lessons.data.api.question_group.ApiQuestion
import com.fx_trading.lessons.data.api.question_group.ApiQuestionGroup
import com.fx_trading.lessons.domain.entities.quiz.Answer
import com.fx_trading.lessons.domain.entities.quiz.Question
import com.fx_trading.lessons.domain.entities.quiz.Questions

fun ApiQuestionGroup.toQuestionGroup(): Questions {
    return Questions(
        available_attempts = this.available_attempts,
        correct_for_success = this.correct_for_success,
        id = this.id,
        is_active = this.is_active,
        language = this.language,
        name = this.name,
        parent_id = this.parent_id,
        questions = this.apiQuestions.map { it.toQuestion() },
        region = this.region,
        isStartExam = this.isStartExam
    )
}

fun ApiQuestion.toQuestion(): Question = Question(answers = this.apiAnswers.map { it.toAnswer() }, description, difficulty, is_active, optional_image_url, title)
fun ApiAnswer.toAnswer(): Answer = Answer(is_active, is_correct, optional_image_url, text)


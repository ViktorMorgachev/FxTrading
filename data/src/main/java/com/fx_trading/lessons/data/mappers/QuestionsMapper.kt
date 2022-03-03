package com.fx_trading.lessons.data.mappers


import com.fx_trading.lessons.data.api.quiz.ApiAnswer
import com.fx_trading.lessons.data.api.quiz.ApiQuiz
import com.fx_trading.lessons.data.api.quiz.ApiQuizGroup
import com.fx_trading.lessons.domain.entities.quiz.Answer
import com.fx_trading.lessons.domain.entities.quiz.Question
import com.fx_trading.lessons.domain.entities.quiz.Questions

fun ApiQuizGroup.toQuizGroup(): Questions {
    return Questions(
        available_attempts = this.available_attempts,
        correct_for_success = this.correct_for_success,
        id = this.id,
        is_active = this.is_active,
        language = this.language,
        name = this.name,
        parent_id = this.parent_id,
        questions = this.quizzes.map { it.toQuiz() },
        region = this.region
    )
}

fun ApiQuiz.toQuiz(): Question = Question(answers = this.answers.map { it.toAnswer() }, description, difficulty, is_active, optional_image_url, title)
fun ApiAnswer.toAnswer(): Answer = Answer(is_active, is_correct, optional_image_url, text)


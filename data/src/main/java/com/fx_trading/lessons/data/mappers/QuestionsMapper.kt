package com.fx_trading.lessons.data.mappers


import com.fx_trading.lessons.data.api.question_group.ApiAnswer
import com.fx_trading.lessons.data.api.question_group.ApiQuestion
import com.fx_trading.lessons.data.api.question_group.ApiQuestionGroup
import com.fx_trading.lessons.domain.entities.quiz.Answer
import com.fx_trading.lessons.domain.entities.quiz.Question
import com.fx_trading.lessons.domain.entities.quiz.QuestionsGroup

fun ApiQuestionGroup?.toQuestionGroup(): QuestionsGroup? {
    if (this == null) return null
    return QuestionsGroup(
        available_attempts = this.available_attempts.toInt(),
        correct_for_success = this.correct_for_success.toInt(),
        id = this.id.toInt(),
        is_active = this.active,
        language = this.language,
        name = this.name,
        parent_id = this.parent_id.toInt(),
        questions = this.questions.map { it.toQuestion() }.toMutableList(),
        region = this.region,
        isStartExam = this.start_exam
    )
}

fun ApiQuestion.toQuestion(): Question = Question(answers = this.answers.map { it.toAnswer() }, description, difficulty.toInt(), active, optional_image_url, title)
fun ApiAnswer.toAnswer(): Answer = Answer(active, correct.toInt() == 1, optional_image_url, text)


package com.learning.lessons.data.mappers


import com.learning.lessons.data.api.question_group.ApiAnswer
import com.learning.lessons.data.api.question_group.ApiQuestion
import com.learning.lessons.data.api.question_group.ApiQuestionGroup
import com.learning.lessons.domain.entities.quiz.Answer
import com.learning.lessons.domain.entities.quiz.Question
import com.learning.lessons.domain.entities.quiz.QuestionsGroup

fun ApiQuestionGroup.toQuestionGroup(): QuestionsGroup {
    return QuestionsGroup(
        available_attempts = this.available_attempts,
        correct_for_success = this.correct_for_success,
        id = this.id,
        is_active = this.active,
        language = this.language,
        name = this.name,
        parent_id = this.parent_id,
        questions = this.questions.map { it.toQuestion() }.toMutableList(),
        region = this.region,
        isStartExam = this.start_exam
    )
}

fun ApiQuestion.toQuestion(): Question = Question(answers = this.answers.map { it.toAnswer() }, description, difficulty, active, optional_image_url, title)
fun ApiAnswer.toAnswer(): Answer = Answer(active, correct == 1, optional_image_url, text)


package com.fx_trading.lessons.features.feature_common.questions

import com.fx_trading.lessons.domain.entities.quiz.Question

interface QuiestionsView {
    fun showQuestion(quiestion: Question, questionSize: Int, step: Int, succesCount: Int)
}
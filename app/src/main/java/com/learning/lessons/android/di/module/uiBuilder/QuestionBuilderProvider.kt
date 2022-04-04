package com.learning.lessons.android.di.module.uiBuilder

import com.learning.feature_common.questions.question.QuestionsFragment
import com.learning.feature_common.questions.question_result.QuestionsResultFragment
import com.learning.feature_common.questions.quiz_result.FirstQuestionsResultFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface QuestionBuilderProvider {

    @ContributesAndroidInjector
    fun questionsFragment() : QuestionsFragment

    @ContributesAndroidInjector
    fun totalQuestionResultFragment(): QuestionsResultFragment

    @ContributesAndroidInjector
    fun totalUserLevelResultFragment(): FirstQuestionsResultFragment

}
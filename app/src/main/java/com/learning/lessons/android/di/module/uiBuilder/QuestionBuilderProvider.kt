package com.learning.lessons.android.di.module.uiBuilder

import com.learning.lessons.feature_main.feature_common.questions.QuestionsFragment
import com.learning.lessons.feature_main.feature_common.questions.question_result.QuestionsResultFragment
import com.learning.lessons.feature_main.feature_common.questions.quiz_result.FirstQuestionsResultFragment
import com.learning.lessons.feature_main.feature_common.test.TestFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface QuestionBuilderProvider {

    @ContributesAndroidInjector
    fun questionsFragment() : QuestionsFragment

    @ContributesAndroidInjector
    fun questionTestFragment() : TestFragment


    @ContributesAndroidInjector
    fun totalQuestionResultFragment(): QuestionsResultFragment

    @ContributesAndroidInjector
    fun totalUserLevelResultFragment(): FirstQuestionsResultFragment

}
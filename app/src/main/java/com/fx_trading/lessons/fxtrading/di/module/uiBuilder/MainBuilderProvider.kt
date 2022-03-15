package com.fx_trading.lessons.fxtrading.di.module.uiBuilder

import com.fx_trading.lessons.feature_main.ui.courses.CoursesFragment
import com.fx_trading.lessons.features.ui.different.DifferentFragment
import com.fx_trading.lessons.features.ui.lesson.LessonFragment
import com.fx_trading.lessons.feature_main.ui.lessons.LessonsFragment
import com.fx_trading.lessons.feature_main.ui.webinars.WebinarsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainBuilderProvider {

    @ContributesAndroidInjector
    fun coursesFragment() : CoursesFragment

    @ContributesAndroidInjector
    fun differentFragment() : DifferentFragment

    @ContributesAndroidInjector
    fun lessonFragment() : LessonFragment

    @ContributesAndroidInjector
    fun lessonsFragment() : LessonsFragment

    @ContributesAndroidInjector
    fun webinarsFragment() : WebinarsFragment
}
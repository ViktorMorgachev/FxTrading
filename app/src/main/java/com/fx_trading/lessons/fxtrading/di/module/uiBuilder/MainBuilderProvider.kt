package com.fx_trading.lessons.fxtrading.di.module.uiBuilder

import com.fx_trading.lessons.feature_main.example.ExampleFragment
import com.fx_trading.lessons.feature_main.ui.courses.CoursesFragment
import com.fx_trading.lessons.features.ui.different.DifferentFragment
import com.fx_trading.lessons.feature_main.ui.lesson.LessonFragment
import com.fx_trading.lessons.feature_main.ui.lessons.LessonsFragment
import com.fx_trading.lessons.feature_main.ui.main.MainFragment
import com.fx_trading.lessons.feature_main.ui.webinar.WebinarFragment
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

    @ContributesAndroidInjector
    fun webinarFragment() : WebinarFragment

    @ContributesAndroidInjector
    fun exampleFragment(): ExampleFragment

    @ContributesAndroidInjector
    fun mainFragment(): MainFragment
}
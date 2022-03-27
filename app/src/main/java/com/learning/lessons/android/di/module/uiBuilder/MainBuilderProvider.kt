package com.learning.lessons.android.di.module.uiBuilder

import com.learning.lessons.feature_main.example.ExampleFragment
import com.learning.lessons.feature_main.ui.courses.CoursesFragment
import com.learning.lessons.features.ui.different.DifferentFragment
import com.learning.lessons.feature_main.ui.lesson.LessonFragment
import com.learning.lessons.feature_main.ui.lessons.LessonsFragment
import com.learning.lessons.feature_main.ui.main.MainFragment
import com.learning.lessons.feature_main.ui.webinar.WebinarFragment
import com.learning.lessons.feature_main.ui.webinars.WebinarsFragment
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
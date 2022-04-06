package com.learning.lessons.android.di.module.uiBuilder

import com.learning.feature_example.example.ExampleFragment
import com.learning.feature_main.article.ArticleFragment
import com.learning.feature_main.articles.ArticlesFragment
import com.learning.feature_main.course.CourseFragment
import com.learning.feature_main.courses.CoursesFragment
import com.learning.feature_main.different.DifferentFragment
import com.learning.feature_main.lesson.LessonFragment
import com.learning.feature_main.lessons.LessonsFragment
import com.learning.feature_main.main.MainFragment
import com.learning.feature_main.webinar.WebinarFragment
import com.learning.feature_main.webinars.WebinarsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainBuilderProvider {

    @ContributesAndroidInjector
    fun coursesFragment() : CoursesFragment

    @ContributesAndroidInjector
    fun courseFragment() : CourseFragment

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

    @ContributesAndroidInjector
    fun articlesFragment(): ArticlesFragment

    @ContributesAndroidInjector
    fun articleFragment(): ArticleFragment
}
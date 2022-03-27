package com.learning.lessons.android.di.module.navigation

import com.learning.navigation.params.screens.ScreenParams
import dagger.MapKey
import kotlin.reflect.KClass


@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ScreenKey(
    val value: KClass<out ScreenParams>
)
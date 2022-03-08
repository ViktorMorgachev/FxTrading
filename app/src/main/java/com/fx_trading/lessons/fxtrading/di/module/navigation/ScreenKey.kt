package com.fx_trading.lessons.fxtrading.di.module.navigation

import com.fx_trading.navigation.params.screens.ScreenParams
import dagger.MapKey
import kotlin.reflect.KClass


@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ScreenKey(
    val value: KClass<out ScreenParams>
)
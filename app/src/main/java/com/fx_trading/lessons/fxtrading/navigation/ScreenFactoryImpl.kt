package com.fx_trading.lessons.fxtrading.navigation

import androidx.fragment.app.Fragment
import com.fx_trading.navigation.ScreenFactory
import com.fx_trading.navigation.params.screens.ScreenParams
import javax.inject.Inject

class ScreenFactoryImpl @Inject constructor() : ScreenFactory {
    override fun getFragment(data: ScreenParams): Fragment? = when (data) {
//        is RegistrationScreenParams -> RegistrationFragment()
        else -> null
    }
}
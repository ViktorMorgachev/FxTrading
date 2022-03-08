package com.fx_trading.lessons.fxtrading.navigation

import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigator
import androidx.navigation.fragment.FragmentNavigator
import com.fx_trading.navigation.NavigationData
import com.fx_trading.navigation.ScreenResolver
import com.fx_trading.navigation.params.screens.ScreenParams
import javax.inject.Inject

class ScreenResolverImpl @Inject constructor(
    private val navigationDataMap: Map<Class<out ScreenParams>, NavigationData>
) : ScreenResolver {

    override fun navigate(
        navController: NavController?,
        data: ScreenParams,
        sharedElements: Map<Any, String>?
    ) {
        val navExtras = toNavExtras(sharedElements)

        navigationDataMap[data::class.java]
            ?.let {
                navController?.navigate(
                    it.navId,
                    it.bundleCreator.createBundle(data),
                    null,
                    navExtras
                )
            }
            ?: run {
//                if (BuildConfig.DEBUG) error("Navigation error, unknown screen data: $data")
            }
    }

    private fun toNavExtras(sharedElements: Map<Any, String>?): Navigator.Extras {
        return FragmentNavigator.Extras.Builder().apply {
            sharedElements?.forEach { (key, value) ->
                (key as? View)?.let { view ->
                    addSharedElement(view, value)
                }
            }
        }.build()
    }

}
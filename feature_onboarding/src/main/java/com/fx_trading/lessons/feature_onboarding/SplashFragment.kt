package com.fx_trading.lessons.feature_onboarding
import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import com.fx_trading.lessons.core.BaseFragment
import com.fx_trading.navigation.Module
import com.fx_trading.navigation.ModuleBinder
import com.fx_trading.navigation.Router
import com.fx_trading.navigation.params.screens.onboarding.SplashFirstScreenParams
import data.DataStoreHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import com.fx_trading.lessons.feature_onboarding.databinding.FragmentSplashBinding as Binding


class SplashFragment : BaseFragment<Binding>() {

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> Binding = Binding::inflate

    @Inject
    lateinit var router: Router
    @Inject
    lateinit var dataStoreHelper: DataStoreHelper

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        CoroutineScope(Dispatchers.IO).launch {
            dataStoreHelper.wasPassedIntro().collect { wasPassedIntro->
                if(!this@SplashFragment.isResumed) return@collect
                if (wasPassedIntro){
                    dataStoreHelper.wasPassedExam().collect { wasPasedExam->
                        if (wasPasedExam){
                            ModuleBinder.gotoToModule(Module.Main, requireActivity())
                        } else {
                            ModuleBinder.gotoToModule(Module.Questions, requireActivity())
                        }
                    }
                } else{
                    withContext(Dispatchers.Main){
                        router.navigate(SplashFirstScreenParams)
                    }

                }
            }
        }
    }
}
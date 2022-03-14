package com.fx_trading.lessons.feature_main.ui.splash
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fx_trading.lessons.core.BaseFragment
import com.fx_trading.lessons.feature_main.activities.MainActivity
import com.fx_trading.lessons.feature_main.activities.QuestionActivity
import com.fx_trading.lessons.features.databinding.FragmentSplashBinding  as Binding
import com.fx_trading.navigation.Router
import com.fx_trading.navigation.params.screens.onboarding.SplashFirstScreenParams
import data.DataStoreHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class SplashFragment : BaseFragment<Binding>() {

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> Binding = Binding::inflate

    @Inject
    lateinit var router: Router
    @Inject
    lateinit var dataStoreHelper: DataStoreHelper

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CoroutineScope(Dispatchers.IO).launch {
            dataStoreHelper.wasPassedIntro().collect { wasPassedIntro->
                if (wasPassedIntro){
                    dataStoreHelper.wasPassedExam().collect { wasPasedExam->
                        if (wasPasedExam){
                            startActivity(Intent(requireActivity(), MainActivity::class.java))
                        } else {
                            startActivity(Intent(requireActivity(), QuestionActivity::class.java))
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
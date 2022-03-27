package com.learning.lessons.feature_main.ui.splash

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.learning.lessons.core.BaseFragment
import com.learning.lessons.feature_main.activities.MainActivity
import com.learning.lessons.feature_main.activities.QuestionActivity
import com.learning.navigation.Router
import com.learning.navigation.params.screens.onboarding.SplashFirstScreenParams
import data.DataStoreHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import com.learning.lessons.features.databinding.FragmentSplashBinding as Binding


class SplashFragment : BaseFragment<Binding>() {

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> Binding = Binding::inflate

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var dataStoreHelper: DataStoreHelper

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Repeat when the lifecycle is RESUMED, cancel when PAUSED
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                dataStoreHelper.wasPassedIntro().collect { wasPassedIntro ->
                    if (wasPassedIntro) {
                        dataStoreHelper.wasPassedExam().collect { wasPasedExam ->
                            if (wasPasedExam) {
                                startActivity(Intent(requireActivity(), MainActivity::class.java))
                            } else {
                                startActivity(
                                    Intent(
                                        requireActivity(),
                                        QuestionActivity::class.java
                                    )
                                )
                            }
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            router.navigate(SplashFirstScreenParams)
                        }

                    }
                }
            }

        }
    }
}
package com.fx_trading.lessons.feature_common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.fx_trading.lessons.core.BaseActivity
import com.fx_trading.lessons.feature_common.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class QuestionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
    }

}
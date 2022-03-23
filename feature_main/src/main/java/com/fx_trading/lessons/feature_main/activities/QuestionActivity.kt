package com.fx_trading.lessons.feature_main.activities

import com.fx_trading.lessons.features.R
import com.fx_trading.navigation.BaseActivity

class QuestionActivity : BaseActivity(R.id.nav_host_fragment_question) {

    companion object{
        val key_question_group_id = "question_group_id"
        val key_lesson_difficulty = "lesson_difficulty"
        val key_lesson_id = "lesson_id"
    }

    override fun initLayout() {
        setContentView(R.layout.activity_question)
    }
}
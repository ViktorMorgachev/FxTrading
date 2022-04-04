package com.learning.feature_common.questions

import com.learning.lessons.features.R
import com.learning.navigation.BaseActivity

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
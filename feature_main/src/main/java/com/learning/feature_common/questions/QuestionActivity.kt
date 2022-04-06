package com.learning.feature_common.questions

import com.learning.lessons.features.R
import com.learning.navigation.BaseActivity

enum class Flag(name: String){
    Lesson("lesson"), Article("article"), FirstExam("first_exam")
}

class QuestionActivity : BaseActivity(R.id.nav_host_fragment_question) {

    companion object{
        val key_id = "object_id"
        val key_flag = "flag"
        val key_question_group_id = "question_group_id"
        val key_object_difficulty = "difficulty"
    }

    override fun initLayout() {
        setContentView(R.layout.activity_question)
    }
}
package com.fx_trading.lessons.feature_main.feature_common.questions

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.paris.Paris
import com.fx_trading.lessons.domain.entities.quiz.Answer
import com.fx_trading.lessons.features.R
import com.fx_trading.lessons.features.databinding.ItemAnswerBinding

class AnswersAdapter(val answers: List<Answer>, val userAnswers: List<Answer> = listOf(), val onSelectedAction: ((Answer) -> Unit)? = null) :
    RecyclerView.Adapter<AnswersAdapter.AnswersHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswersHolder {
        val itemBinding = ItemAnswerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnswersHolder(itemBinding, onSelectedAction, userAnswers)
    }

    override fun onBindViewHolder(holder: AnswersHolder, position: Int) {
        val answer = answers[position]
        holder.bind(answer)
    }

    override fun getItemCount(): Int = answers.size


    class AnswersHolder(private val itemBinding: ItemAnswerBinding, val onSelectedAction: ((Answer) -> Unit)?, val userAnswers: List<Answer>) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(answer: Answer) {
            with(itemBinding){
                answerButton.text = answer.text
                if (userAnswers.isEmpty()){
                    answerButton.isEnabled = true
                    itemBinding.answerButton.setOnClickListener {
                        answerButton.answerSetSelected()
                        onSelectedAction?.invoke(answer)
                    }
                    return@with
                }
                if (userAnswers.contains(answer)){
                    if (answer.is_correct){
                        answerButton.answerSetCorrect()
                        answerButton.text = answer.text
                    } else {
                        answerButton.answerSetIncorrect()
                        answerButton.text = answer.text
                    }
                } else {
                    answerButton.answerSetDefault()
                    answerButton.text = answer.text
                }
            }
        }
    }
}
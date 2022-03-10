package com.fx_trading.lessons.feature_common.ui.questions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.paris.Paris
import com.fx_trading.lessons.domain.entities.quiz.Answer
import com.fx_trading.lessons.feature_common.R
import com.fx_trading.lessons.feature_common.databinding.ItemAnswerBinding

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
                        Paris.style(it).apply(R.style.quiz_button_selected)
                        onSelectedAction?.invoke(answer)
                    }
                    return@with
                }
                if (userAnswers.contains(answer)){
                    if (answer.is_correct){
                        Paris.style(answerButton).apply(R.style.quiz_button_correct)
                    } else {
                        Paris.style(answerButton).apply(R.style.quiz_button_incorrect)
                    }
                } else {
                    Paris.style(answerButton).applyDefault()
                }
            }
        }
    }
}
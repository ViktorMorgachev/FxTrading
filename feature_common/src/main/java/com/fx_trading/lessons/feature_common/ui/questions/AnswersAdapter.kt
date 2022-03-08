package com.fx_trading.lessons.feature_common.ui.questions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fx_trading.lessons.domain.entities.quiz.Answer
import com.fx_trading.lessons.feature_common.databinding.ItemAnswerBinding

class AnswersAdapter(private val answers: List<Answer>) : RecyclerView.Adapter<AnswersAdapter.AnswersHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswersHolder {
        val itemBinding = ItemAnswerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnswersHolder(itemBinding)
    }
    override fun onBindViewHolder(holder: AnswersHolder, position: Int) {
        val paymentBean = answers[position]
        holder.bind(paymentBean)
    }

    override fun getItemCount(): Int = answers.size

    class AnswersHolder(private val itemBinding: ItemAnswerBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(answer: Answer) {
            itemBinding.answerButton.text = answer.text
        }
    }
}
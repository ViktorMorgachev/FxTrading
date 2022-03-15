package com.fx_trading.lessons.feature_main.ui.custom

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fx_trading.lessons.domain.entities.lesson.Lesson
import com.fx_trading.lessons.features.databinding.AccordeonItemBinding

class LessonsAdapter(val data: List<Lesson>):  RecyclerView.Adapter<LessonsAdapter.LessonsHolder>() {

    class LessonsHolder(private val itemBinding: AccordeonItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(answer: Lesson) {
            with(itemBinding){

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonsHolder {
        val itemBinding = AccordeonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LessonsHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: LessonsHolder, position: Int) {
        val lesson = data[position]
        holder.bind(lesson)
    }

    override fun getItemCount(): Int {
       return data.size
    }
}
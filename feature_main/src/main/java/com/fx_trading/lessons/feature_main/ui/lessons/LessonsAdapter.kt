package com.fx_trading.lessons.feature_main.ui.lessons

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fx_trading.lessons.domain.entities.lesson.Lesson
import com.fx_trading.lessons.features.databinding.AccordeonItemBinding

class LessonsAdapter(val data: List<Lesson>,val openLessonAction: (Lesson)->Unit, val likeLessonAction: (Int)->Unit, val completedLessonIDs: List<Int>):  RecyclerView.Adapter<LessonsAdapter.LessonsHolder>() {

    class LessonsHolder(private val itemBinding: AccordeonItemBinding, val openLessonAction: (Lesson)->Unit, val likeLessonAction: (Int)->Unit, val completedLessonIDs: List<Int>) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(lesson: Lesson) {
            with(itemBinding){
                countOfLikes.text = "${lesson.likes}"
                marketingTitle.text = lesson.marketing_title
                Glide.with(itemView).load(lesson.promo_image_url).into(ivPromoLesson)
                tvVideoDuration.text = lesson.duration
                if (completedLessonIDs.contains(lesson.id)){
                    lessonItemRoot.setBackgroundColor(Color.parseColor("#C8E6C9"))
                }
                lessonItemRoot.setOnClickListener {
                    openLessonAction(lesson)
                }
                lessonItemRoot.setOnClickListener {
                    likeLessonAction(lesson.id)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonsHolder {
        val itemBinding = AccordeonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LessonsHolder(itemBinding,openLessonAction, likeLessonAction, completedLessonIDs)
    }

    override fun onBindViewHolder(holder: LessonsHolder, position: Int) {
        val lesson = data[position]
        holder.bind(lesson)
    }

    override fun getItemCount(): Int {
       return data.size
    }
}
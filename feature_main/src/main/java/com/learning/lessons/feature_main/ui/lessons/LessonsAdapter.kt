package com.learning.lessons.feature_main.ui.lessons

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.learning.lessons.domain.entities.lesson.Lesson
import com.learning.lessons.features.R
import com.learning.lessons.features.databinding.LessonItemBinding

class LessonsAdapter(var data: List<Lesson>, val openLessonAction: (Lesson)->Unit, val likeLessonAction: (Int)->Unit, val completedLessonIDs: List<Int>):  RecyclerView.Adapter<LessonsAdapter.LessonsHolder>() {

    init {
        data = data.sortedBy { it.id }
    }

    // TODO грязный подход, нужно будет потом обязательно переделать
    companion object{
        val actualLessons = mutableSetOf<Lesson>()
    }

    class LessonsHolder(private val itemBinding: LessonItemBinding, val openLessonAction: (Lesson)->Unit, val likeLessonAction: (Int)->Unit, val completedLessonIDs: List<Int>) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(lesson: Lesson) {
            val actualLesson = actualLessons.firstOrNull { it.id == lesson.id } ?: lesson
            with(itemBinding){
                countOfLikes.text = "${actualLesson.likes}"
                Glide.with(itemView.context)
                    .load(actualLesson.promo_image_url).error(R.drawable.mock_video_image)
                    .into(ivPromoLesson)
                tvVideoDuration.text = actualLesson.duration
                title.text  = actualLesson.title
                if (completedLessonIDs.contains(actualLesson.id)){
                    lessonItemRoot.setBackgroundColor(Color.parseColor("#C8E6C9"))
                }
                lessonItemRoot.setOnClickListener {
                    openLessonAction(actualLesson)
                }
                difficultyItem.setDifficulty(lesson.difficulty)
                ivLike.setOnClickListener {
                    likeLessonAction(actualLesson.id)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonsHolder {
        val itemBinding = LessonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
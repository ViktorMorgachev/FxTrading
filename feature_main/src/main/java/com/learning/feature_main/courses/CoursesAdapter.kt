package com.learning.feature_main.courses

import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.learning.lessons.domain.entities.course.Course
import com.learning.lessons.domain.entities.lesson.Lesson
import com.learning.lessons.features.R
import com.learning.lessons.features.databinding.CourseItemBinding
import com.learning.lessons.features.databinding.LessonItemBinding
import com.learning.lessons.utils.utils.gone
import com.learning.lessons.utils.utils.visible
import data.formatDate
import data.isFuture

class CoursesAdapter(var data: List<Course>, val openCourseAction: (Course)->Unit, val likeCourseAction: (Int)->Unit, val completedCoursesIDs: List<Int>):  RecyclerView.Adapter<CoursesAdapter.CourseHolder>() {

    init {
        data = data.sortedBy { it.id }
    }

    // TODO грязный подход, нужно будет потом обязательно переделать
    companion object{
        val actualCourses = mutableSetOf<Course>()
    }

   inner class CourseHolder(private val itemBinding: CourseItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(course: Course) {
            val actualCourse = actualCourses.firstOrNull { it.id == course.id } ?: course
            with(itemBinding){
               likeItemRoot.countOfLikes.text = "${actualCourse.likes}"
                Glide.with(itemView.context)
                    .load(actualCourse.promo_image_url).error(R.drawable.mock_video_background)
                    .into(ivPromoCourse)
                title.text  = course.title
                if (completedCoursesIDs.contains(actualCourse.id)){
                    courseItemRoot.setBackgroundColor(Color.parseColor("#C8E6C9"))
                }
                courseItemRoot.setOnClickListener {
                    openCourseAction(actualCourse)
                }
                difficultyItem.setDifficulty(course.difficulty)
                likeItemRoot.ivLike.setOnClickListener {
                    likeCourseAction(actualCourse.id)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseHolder {
        val itemBinding = CourseItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CourseHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CourseHolder, position: Int) {
        val course = data[position]
        holder.bind(course)
    }

    override fun getItemCount(): Int {
       return data.size
    }
}
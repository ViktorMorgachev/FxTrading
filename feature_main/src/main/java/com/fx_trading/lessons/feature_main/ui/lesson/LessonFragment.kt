package com.fx_trading.lessons.feature_main.ui.lesson

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.fx_trading.common.State
import com.fx_trading.common.setDifficulty
import com.fx_trading.lessons.core.BaseFragment
import com.fx_trading.lessons.core.BaseViewModelFactory
import com.fx_trading.lessons.domain.entities.lesson.Lesson
import com.fx_trading.lessons.feature_main.activities.QuestionActivity
import com.fx_trading.lessons.feature_main.activities.QuestionActivity.Companion.key_lesson_difficulty
import com.fx_trading.lessons.feature_main.activities.QuestionActivity.Companion.key_lesson_id
import com.fx_trading.lessons.feature_main.activities.QuestionActivity.Companion.key_question_group_id
import com.fx_trading.lessons.feature_main.ui.lessons.LessonsAdapter
import com.fx_trading.lessons.features.R
import com.fx_trading.lessons.features.databinding.FragmentLessonBinding
import com.fx_trading.lessons.utils.utils.gone
import com.fx_trading.lessons.utils.utils.isGone
import com.fx_trading.lessons.utils.utils.visible
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.coroutines.flow.collect
import javax.inject.Inject
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions




class LessonFragment : BaseFragment<FragmentLessonBinding>() {

    @Inject
    lateinit var viewModelFactory: BaseViewModelFactory<LessonViewModel>

    private val viewModel: LessonViewModel by viewModels(
        factoryProducer = { viewModelFactory }
    )

    var youTubePlayer: YouTubePlayer? = null

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLessonBinding =
        FragmentLessonBinding::inflate

    private fun showLesson(lesson: Lesson) {
        with(binding) {

            if (youTubePlayer == null){
                youtubePlayerView.enableAutomaticInitialization = false


                youtubePlayerView.initialize(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        this@LessonFragment.youTubePlayer = youTubePlayer
                        val videoId =  lesson.video_url.substringAfter("v=").substringBefore("&")
                        youTubePlayer.cueVideo(videoId, 0F)
                        val timecodes = lesson.timecodes.filter { it.is_active && it.timeSeconds > 0 && it.title.isNotEmpty() && it.time.isNotEmpty()}
                        if (timecodes.isNotEmpty()){
                            itemTimecodes.visible()
                            recyclerTimecodes.adapter = TimecodesAdapter(data = timecodes){
                                youTubePlayer.loadVideo(videoId, it.toFloat())
                            }
                            ivArrowTimecodes.setOnClickListener {
                                if (recyclerTimecodes.isGone()){
                                    recyclerTimecodes.visible()
                                    ivArrowTimecodes.rotation = 0f
                                } else {
                                    ivArrowTimecodes.rotation = -90f
                                    recyclerTimecodes.gone()
                                }
                            }
                        } else {
                            itemTimecodes.gone()
                            recyclerTimecodes.gone()
                        }

                    }
                }, false, IFramePlayerOptions.Builder()
                    .controls(1)
                    .rel(0)
                    .ivLoadPolicy(3)
                    .ccLoadPolicy(1)
                    .build())


            } else {
                val videoId = lesson.video_url.substringAfter("v=").substringBefore("&")
                youTubePlayer?.cueVideo(videoId, 0F)
                val timecodes = lesson.timecodes.filter { it.is_active && it.timeSeconds > 0 && it.title.isNotEmpty() && it.time.isNotEmpty()}
                if (timecodes.isNotEmpty()) {
                    itemTimecodes.visible()
                    recyclerTimecodes.adapter = TimecodesAdapter(data = timecodes) {
                        youTubePlayer?.loadVideo(videoId, it.toFloat())
                    }
                    ivArrowTimecodes.setOnClickListener {
                        if (recyclerTimecodes.isGone()) {
                            recyclerTimecodes.visible()
                            ivArrowTimecodes.rotation = 0f
                        } else {
                            ivArrowTimecodes.rotation = -90f
                            recyclerTimecodes.gone()
                        }
                    }
                }
            }

            startExam.setOnClickListener {
                val intent = Intent(requireActivity(), QuestionActivity::class.java)
                intent.putExtra(key_question_group_id, lesson.question_group)
                intent.putExtra(key_lesson_difficulty, lesson.difficulty)
                intent.putExtra(key_lesson_id, lesson.id)
                startActivity(intent)
            }
            toolbar.cancelButton.setOnClickListener {
                findNavController().popBackStack()
            }
            likeDislikeItem.dislikeItem.ivLike.setOnClickListener {
                lifecycleScope.launchWhenResumed {
                    viewModel.dislikeLesson(lessonID = lesson.id).collect { state ->
                        when (state) {
                            is State.DataState -> {
                                likeDislikeItem.dislikeItem.ivLike.setImageDrawable(
                                    ResourcesCompat.getDrawable(
                                        resources,
                                        R.drawable.ic_dislike_full,
                                        it.context.theme
                                    )
                                )
                                likeDislikeItem.likeItem.ivLike.setImageDrawable(
                                    ResourcesCompat.getDrawable(
                                        resources,
                                        R.drawable.ic_like_white,
                                        it.context.theme
                                    )
                                )

                            }
                        }
                    }
                }
            }
            likeDislikeItem.likeItem.root.setOnClickListener {
                lifecycleScope.launchWhenResumed {
                    viewModel.likeLesson(lessonID = lesson.id).collect { state ->
                        when (state) {
                            is State.DataState -> {
                                likeDislikeItem.likeItem.tvLikeText.text = "${state.data.likes}"
                                likeDislikeItem.likeItem.ivLike.setImageDrawable(
                                    ResourcesCompat.getDrawable(
                                        resources,
                                        R.drawable.ic_like_full,
                                        it.context.theme
                                    )
                                )
                                likeDislikeItem.dislikeItem.ivLike.setImageDrawable(
                                    ResourcesCompat.getDrawable(
                                        resources,
                                        R.drawable.ic_dislike,
                                        it.context.theme
                                    )
                                )
                            }
                        }
                    }
                }

            }
            tvVideoAutor.text = lesson.speaker_name
            tvVideoTitle.text = lesson.title
            difficultyItem.setDifficulty(lesson.difficulty)
            difficultyName.setDifficulty(lesson.difficulty)
            likeDislikeItem.likeItem.tvLikeText.text = "${lesson.likes}"
            toolbar.toolbarText.text = toolbar.toolbarText.resources.getString(R.string.lesson)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val lessonID = arguments?.getInt("lesson_id") ?: -1
        with(binding) {
            recyclerRecommendLessons.layoutManager = LinearLayoutManager(requireContext())
            recyclerTimecodes.layoutManager = LinearLayoutManager(requireContext())
            lifecycle.addObserver(youtubePlayerView)
            recyclerTimecodes.gone()
            itemTimecodes.gone()
        }
        lifecycleScope.launchWhenResumed {
            showLessonByLessonID(lessonID = lessonID)
        }
    }

    private suspend fun showLessonByLessonID(lessonID: Int) {
        viewModel.getLesson(lessonID = lessonID).collect {
            when (it) {
                is State.DataState -> {
                    showLesson(it.data)
                    lifecycleScope.launchWhenResumed {
                        viewModel.getLessonsByTags(it.data.tags).collect {
                            when(it){
                                is State.DataState -> {
                                    showLessons(it.data.first, it.data.second)
                                }
                            }
                        }
                    }

                }
            }
        }
    }

    private fun showLessons(data: List<Lesson>, completedLessons: List<Int>) {
        with(binding){
            val likeLessonAction: (Int)->Unit = { lessonID->
                lifecycleScope.launchWhenCreated {
                    viewModel.likeLesson(lessonID = lessonID).collect { state ->
                        when (state) {
                            is State.DataState -> {
                                LessonsAdapter.actualLessons.add(state.data)

                            }
                        }
                    }
                }
            }
            val openLessonAction: (Lesson)->Unit = { lesson->
                lifecycleScope.launchWhenResumed {
                    showLessonByLessonID(lessonID = lesson.id)
                }
            }
            val dataForList = if (data.size > 3){
                showMoreButton.visible()
                data.take(3)
            } else data

            showMoreButton.setOnClickListener {
                recyclerRecommendLessons.adapter = LessonsAdapter(data = data, openLessonAction = openLessonAction, likeLessonAction = likeLessonAction, completedLessonIDs = completedLessons)
                showMoreButton.gone()
            }
            recyclerRecommendLessons.adapter = LessonsAdapter(data = dataForList, openLessonAction = openLessonAction, likeLessonAction = likeLessonAction, completedLessonIDs = completedLessons)
        }
    }
}
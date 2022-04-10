package com.learning.feature_main.lesson

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.learning.common.State
import com.learning.common.setDifficulty
import com.learning.feature_common.questions.Flag
import com.learning.lessons.core.BaseFragment
import com.learning.lessons.core.BaseViewModelFactory
import com.learning.lessons.domain.entities.lesson.Lesson
import com.learning.feature_common.questions.QuestionActivity
import com.learning.feature_common.questions.QuestionActivity.Companion.key_flag
import com.learning.feature_common.questions.QuestionActivity.Companion.key_id
import com.learning.feature_common.questions.QuestionActivity.Companion.key_object_difficulty
import com.learning.feature_common.questions.QuestionActivity.Companion.key_question_group_id
import com.learning.feature_main.lessons.LessonsAdapter
import com.learning.lessons.features.R
import com.learning.lessons.features.databinding.FragmentLessonBinding
import com.learning.lessons.utils.utils.gone
import com.learning.lessons.utils.utils.isGone
import com.learning.lessons.utils.utils.visible
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


class LessonFragment : BaseFragment<FragmentLessonBinding>() {

    @Inject
    lateinit var viewModelFactory: BaseViewModelFactory<LessonViewModel>

    private val viewModel: LessonViewModel by viewModels(
        factoryProducer = { viewModelFactory }
    )

    var youTubePlayer: YouTubePlayer? = null
    private var currentPlayerState: PlayerConstants.PlayerState =
        PlayerConstants.PlayerState.UNKNOWN

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLessonBinding =
        FragmentLessonBinding::inflate

    private fun showLesson(lesson: Lesson) {
        with(binding) {

            if (youTubePlayer == null){
                youtubePlayerView.enableAutomaticInitialization = false

                youtubePlayerView.initialize(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        this@LessonFragment.youTubePlayer = youTubePlayer
                        updateVideoContent(lesson)
                    }

                    override fun onStateChange(
                        youTubePlayer: YouTubePlayer,
                        state: PlayerConstants.PlayerState
                    ) {
                        this@LessonFragment.currentPlayerState = state
                    }

                }, false, IFramePlayerOptions.Builder()
                    .controls(1)
                    .rel(0)
                    .ivLoadPolicy(3)
                    .ccLoadPolicy(1)
                    .build())


            } else {
               updateVideoContent(lesson)
            }
            if (lesson.question_group > 0){
                startQuizBottomRoot.startQuizBottom.visible()
                startQuizBottomRoot.startExam.setOnClickListener {
                    val intent = Intent(requireActivity(), QuestionActivity::class.java)
                    intent.putExtra(key_question_group_id, lesson.question_group)
                    intent.putExtra(key_object_difficulty, lesson.difficulty)
                    intent.putExtra(key_id, lesson.id)
                    intent.putExtra(key_flag, Flag.Lesson.name)
                    startActivity(intent)
                }
            } else {
                startQuizBottomRoot.startQuizBottom.gone()
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

    private fun updateVideoContent(lesson: Lesson) {
        with(binding){
            val videoId =  lesson.video_url.substringAfter("v=").substringBefore("&")
            if (currentPlayerState == PlayerConstants.PlayerState.UNKNOWN)
            youTubePlayer?.cueVideo(videoId, 0F)
            val timecodes = lesson.timecodes.filter { it.timeSeconds > 0 && it.title.isNotEmpty() && it.time.isNotEmpty()}
            if (timecodes.isNotEmpty()){
                itemTimecodes.visible()
                recyclerTimecodes.adapter = TimecodesAdapter(data = timecodes){
                    youTubePlayer?.loadVideo(videoId, it.toFloat())
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
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                showLessonByLessonID(lessonID = lessonID)
            }
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED){
                viewModel.subscribeToLessons().collect {
                    showLessonByLessonID(lessonID)
                }
            }
        }

    }

    private suspend fun showLessonByLessonID(lessonID: Int) {
        assert(lessonID > 0)
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
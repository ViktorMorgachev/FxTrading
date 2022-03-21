package com.fx_trading.lessons.feature_main.ui.lesson

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
import com.fx_trading.lessons.core.BaseFragment
import com.fx_trading.lessons.core.BaseViewModelFactory
import com.fx_trading.lessons.domain.entities.lesson.Lesson
import com.fx_trading.lessons.feature_main.ui.lessons.LessonsAdapter
import com.fx_trading.lessons.features.R
import com.fx_trading.lessons.features.databinding.FragmentLessonBinding
import com.fx_trading.lessons.utils.utils.gone
import com.fx_trading.lessons.utils.utils.isGone
import com.fx_trading.lessons.utils.utils.visible
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class LessonFragment : BaseFragment<FragmentLessonBinding>() {

    @Inject
    lateinit var viewModelFactory: BaseViewModelFactory<LessonViewModel>

    private val viewModel: LessonViewModel by viewModels(
        factoryProducer = { viewModelFactory }
    )

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLessonBinding =
        FragmentLessonBinding::inflate

    private fun showLesson(lesson: Lesson) {
        with(binding) {
            youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
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
            })

            toolbar.cancelButton.setOnClickListener {
                findNavController().popBackStack()
            }
            likeDislikeItem.dislikeItem.ivLike.setOnClickListener {
                lifecycleScope.launchWhenResumed {
                    viewModel.dislikeLesson(lessonID = lesson.id.toLong()).collect { state ->
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
                    viewModel.likeLesson(lessonID = lesson.id.toLong()).collect { state ->
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
            tvVideoDescription.text = lesson.description
            likeDislikeItem.likeItem.tvLikeText.text = "${lesson.likes}"
            toolbar.toolbarText.text = lesson.title
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
            viewModel.getLesson(lessonID = lessonID.toLong()).collect {
                when (it) {
                    is State.DataState -> {
                        showLesson(it.data)
                        lifecycleScope.launchWhenResumed {
                            viewModel.getLessonsByTags(it.data.tags).collect {
                                when(it){
                                    is State.DataState -> {
                                        showLessons(it.data)
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }
    }

    private fun showLessons(data: List<Lesson>) {
        with(binding){
            val dataForList = if (data.size > 3){
                data.take(3)
            } else data
            showMoreButton.setOnClickListener {
                recyclerRecommendLessons.adapter = LessonsAdapter(data = data, openLessonAction = {}, likeLessonAction = {}, completedLessonIDs = listOf())
                showMoreButton.gone()
            }
            recyclerRecommendLessons.adapter = LessonsAdapter(data = dataForList, openLessonAction = {}, likeLessonAction = {}, completedLessonIDs = listOf())
        }
    }
}
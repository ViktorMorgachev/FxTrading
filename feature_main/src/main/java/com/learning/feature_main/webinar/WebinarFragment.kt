package com.learning.feature_main.webinar

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.learning.common.State
import com.learning.feature_main.lesson.TimecodesAdapter
import com.learning.lessons.core.BaseFragment
import com.learning.lessons.core.BaseViewModelFactory
import com.learning.lessons.domain.entities.webinar.Webinar
import com.learning.lessons.features.R
import com.learning.lessons.features.databinding.FragmentWebinarBinding
import com.learning.lessons.utils.utils.Logger
import com.learning.lessons.utils.utils.gone
import com.learning.lessons.utils.utils.isGone
import com.learning.lessons.utils.utils.visible
import com.learning.navigation.Router
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import data.formatDateTime
import data.isFuture
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class WebinarFragment : BaseFragment<FragmentWebinarBinding>() {

    @Inject
    lateinit var viewModelFactory: BaseViewModelFactory<WebinarViewModel>

    private val viewModel: WebinarViewModel by viewModels(
        factoryProducer = { viewModelFactory }
    )

    @Inject
    lateinit var router: Router

    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentWebinarBinding =
        FragmentWebinarBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            recyclerTimecodes.layoutManager = LinearLayoutManager(requireContext())
            lifecycle.addObserver(youtubePlayerView)
            recyclerTimecodes.gone()
            itemTimecodes.gone()
        }
        lifecycleScope.launchWhenCreated {
            arguments?.getInt("webinar_id")?.let { webinarID ->
                viewModel.getWebinarByID(webinarID).collect { state->
                    when(state){
                        is State.DataState ->{
                            showWebinar(state.data)
                        }
                    }
                }
            }

        }

    }

    private fun showWebinar(webinar: Webinar) {
        with(binding){
            youtubePlayerView.enableAutomaticInitialization = false
            youtubePlayerView.initialize(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    val videoId =  webinar.video_url.substringAfter("v=").substringBefore("&")
                    youTubePlayer.cueVideo(videoId, 0F)
                    val timecodes = webinar.timecodes.filter {  it.timeSeconds > 0 && it.title.isNotEmpty() && it.time.isNotEmpty()}
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

            toolbar.cancelButton.setOnClickListener {
                throw  RuntimeException("Test Crash"); // Force a crash
                findNavController().popBackStack()
            }
            tvVideoAutor.text = webinar.speaker_name
            tvVideoTitle.text = webinar.title
            if (webinar.webinar_date.isFuture()){
                likeDislikeItem.root.gone()
                signUpBottom.visible()
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    webinarDateTime.text = webinar.webinar_date.formatDateTime()
                }
                toolbar.toolbarText.text = root.resources.getString(R.string.webinar_upcoming)
                sighUp.setOnClickListener {
                    try {
                        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(webinar.calendar_url))
                        startActivity(browserIntent)
                    } catch (e: Exception){
                        Logger.log("WebinarFragment", exception = e)
                    }
                }

            } else {

                toolbar.toolbarText.text = toolbar.toolbarText.resources.getString(R.string.webinar)
                likeDislikeItem.root.visible()
                signUpBottom.gone()
                likeDislikeItem.likeItem.tvLikeText.text = webinar.likes.toString()

                likeDislikeItem.dislikeItem.ivLike.setOnClickListener {
                    lifecycleScope.launchWhenResumed {
                        viewModel.dislikeWebinar(webinarID = webinar.id).collect { state ->
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
                        viewModel.likeWebinar(webinarID = webinar.id).collect { state ->
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

            }
            description.text = webinar.description
        }

    }

}
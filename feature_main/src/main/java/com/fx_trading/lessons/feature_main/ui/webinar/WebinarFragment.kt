package com.fx_trading.lessons.feature_main.ui.webinar

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
import androidx.navigation.fragment.findNavController
import com.fx_trading.common.State
import com.fx_trading.lessons.core.BaseFragment
import com.fx_trading.lessons.core.BaseViewModelFactory
import com.fx_trading.lessons.domain.entities.webinar.Webinar
import com.fx_trading.lessons.features.R
import com.fx_trading.lessons.features.databinding.FragmentWebinarBinding
import com.fx_trading.lessons.utils.utils.Logger
import com.fx_trading.lessons.utils.utils.gone
import com.fx_trading.lessons.utils.utils.visible
import com.fx_trading.navigation.Router
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
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
            lifecycle.addObserver(youtubePlayerView)
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
            youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    val videoId = webinar.video_url.substringAfter("v=").substringBefore("&")
                    youTubePlayer.cueVideo(videoId, 0F)
                }
            })
            toolbar.cancelButton.setOnClickListener {
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
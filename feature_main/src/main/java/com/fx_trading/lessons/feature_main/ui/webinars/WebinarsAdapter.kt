package com.fx_trading.lessons.feature_main.ui.webinars

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fx_trading.lessons.domain.entities.webinar.Webinar
import com.fx_trading.lessons.features.R
import com.fx_trading.lessons.features.databinding.WebinarItemBinding
import com.fx_trading.lessons.utils.utils.gone
import com.fx_trading.lessons.utils.utils.visible
import data.formatDate
import data.isFuture
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

class WebinarsAdapter(var data: List<Webinar>): RecyclerView.Adapter<WebinarsAdapter.WebinarsHolder>() {

    init {
        data = data.sortedBy { it.sort_order }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WebinarsHolder {
        val itemBinding = WebinarItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WebinarsHolder(itemBinding, data = data)
    }

    override fun onBindViewHolder(holder: WebinarsHolder, position: Int) {
        val data = data[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = data.size

    class WebinarsHolder(private val itemBinding: WebinarItemBinding, val data: List<Webinar>) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(data: Webinar) {
            with(itemBinding){

                if (data.webinar_date.isFuture()){
                    dateOfWebinar.visible()
                    likeItemRoot.gone()
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        dateOfWebinar.text = data.webinar_date.formatDate()
                    }
                    webinarTopText.text = root.resources.getString(R.string.webinar_upcoming)
                } else{
                    likeItemRoot.visible()
                    dateOfWebinar.gone()
                    countOfLikes.text = "${data.likes}"
                    countOfLikes.setOnClickListener {

                    }
                    webinarTopText.text = root.resources.getString(R.string.webinar)
                }
                Glide.with(itemView.context)
                    .load(data.promo_image_url).error(R.drawable.mock_video_image)
                    .into(ivPromoWebinar)
                title.text = data.title
            }
        }

    }
}
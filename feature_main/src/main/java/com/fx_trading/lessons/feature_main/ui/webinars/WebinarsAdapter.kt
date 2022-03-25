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

class WebinarsAdapter(
    var data: MutableList<Webinar>,
    val openWebinarAction: (Int) -> Unit,
    val onLikeWebinarAction: (Int) -> Unit
) : RecyclerView.Adapter<WebinarsAdapter.WebinarsHolder>() {

    init {
        data = data.sortedBy { it.sort_order }.toMutableList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WebinarsHolder {
        val itemBinding =
            WebinarItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WebinarsHolder(itemBinding, data = data)
    }

    override fun onBindViewHolder(holder: WebinarsHolder, position: Int) {
        val data = data[position]
        holder.bind(data)
    }

    fun replaceItem(webinar: Webinar) {
        val index = data.indexOf(data.first { it.id == webinar.id })
        data.removeAt(index)
        data.add(index, webinar)
        notifyItemInserted(index)
        notifyItemRangeChanged(index, data.size)
    }



    override fun getItemCount(): Int = data.size

    inner class WebinarsHolder(
        private val itemBinding: WebinarItemBinding,
        val data: List<Webinar>
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(webinar: Webinar) {
            with(itemBinding) {

                if (webinar.webinar_date.isFuture()) {
                    dateOfWebinar.visible()
                    likeItemRoot.gone()
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        dateOfWebinar.text = webinar.webinar_date.formatDate()
                    }
                    webinarTopText.text = root.resources.getString(R.string.webinar_upcoming)
                } else {
                    likeItemRoot.visible()
                    dateOfWebinar.gone()
                    countOfLikes.text = "${webinar.likes}"
                    likeItemRoot.setOnClickListener{
                        onLikeWebinarAction.invoke(webinar.id)
                    }
                    countOfLikes.setOnClickListener {
                        onLikeWebinarAction.invoke(webinar.id)
                    }
                    webinarTopText.text = root.resources.getString(R.string.webinar)
                }
                webinarItemRoot.setOnClickListener {
                    openWebinarAction.invoke(webinar.id)
                }
                Glide.with(itemView.context)
                    .load(webinar.promo_image_url).error(R.drawable.mock_video_image)
                    .into(ivPromoWebinar)
                title.text = webinar.title
            }
        }



    }
}
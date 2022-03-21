package com.fx_trading.lessons.feature_main.ui.lesson

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fx_trading.lessons.domain.entities.common.Timecode
import com.fx_trading.lessons.features.databinding.TimecodeItemBinding

class TimecodesAdapter(val data: List<Timecode>, val  onItemClickListener: (Long)->Unit): RecyclerView.Adapter<TimecodesAdapter.TimecodesHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimecodesHolder {
        val itemBinding = TimecodeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TimecodesHolder(itemBinding, data = data)
    }

    override fun onBindViewHolder(holder: TimecodesHolder, position: Int) {
        val data = data[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = data.size

    inner class TimecodesHolder(private val itemBinding: TimecodeItemBinding, val data: List<Timecode>) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(data: Timecode) {
            with(itemBinding){
                tvTime.text = data.time
                tvTibeDiscribe.text = data.title
                this.root.setOnClickListener {
                    onItemClickListener(data.timeSeconds)
                }
            }
        }

    }
}
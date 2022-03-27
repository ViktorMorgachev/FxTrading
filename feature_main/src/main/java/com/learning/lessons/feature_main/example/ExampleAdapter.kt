package com.learning.lessons.feature_main.example

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.learning.lessons.features.databinding.ItemAnswerBinding

class ExampleAdapterRecyclerView(val data: List<Any>): RecyclerView.Adapter<ExampleAdapterRecyclerView.ExampleHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleHolder {
        val itemBinding = ItemAnswerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExampleHolder(itemBinding, data = data)
    }

    override fun onBindViewHolder(holder: ExampleHolder, position: Int) {
        val data = data[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = data.size


    class ExampleHolder(private val itemBinding: ItemAnswerBinding, val data: List<Any>) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(data: Any) {
            with(itemBinding){

            }
        }

    }
}
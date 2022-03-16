package com.fx_trading.lessons.feature_main.ui.custom

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fx_trading.lessons.feature_main.ui.lessons.LessonsAdapter
import com.fx_trading.lessons.features.databinding.AccordionViewBinding
import com.fx_trading.lessons.utils.utils.gone
import com.fx_trading.lessons.utils.utils.visible

open class BaseAccordionAdapter(
    val data: List<AccordionData<LessonsAdapter>>,
    val accordionCollapsedAction: () -> Unit,
    val accordionExpandedAction: () -> Unit,
) : RecyclerView.Adapter<BaseAccordionAdapter.AccordionListHolder>() {

    private val expandedItemIndex = 0

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AccordionListHolder {
        val itemBinding = AccordionViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AccordionListHolder(
            itemBinding,
            data = data,
            accordionCollapsedAction = accordionCollapsedAction,
            accordionExpandedAction = accordionExpandedAction,
            expandedItemIndex
        )
    }

 inner class AccordionListHolder(
        val itemBinding: AccordionViewBinding,
        val data: List<AccordionData<LessonsAdapter>>,
        val accordionCollapsedAction: () -> Unit,
        val accordionExpandedAction: () -> Unit,
        val expandedItemIndex: Int,
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(data: AccordionData<LessonsAdapter>) {
            with(itemBinding) {
                this.tvCategory.text = data.accordionTittle
                this.accordionsRecyclerView.layoutManager = LinearLayoutManager(this.root.context)
                this.accordionsRecyclerView.adapter = data.accordionListAdapter
                this.ivArrow.setOnClickListener {
                    if (data.expanded){
                        collapseView(data)
                    } else expandView(data)
                }
            }
        }

       private fun expandView(data: AccordionData<LessonsAdapter>) {
            with(itemBinding) {
                ivArrow.rotation = 180F
                accordionsRecyclerView.visible()
                data.expanded = !data.expanded
            }

        }

       private fun collapseView(data: AccordionData<LessonsAdapter>) {
            with(itemBinding) {
                ivArrow.rotation = 0F
                accordionsRecyclerView.gone()
                data.expanded = !data.expanded
            }

        }

    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(
        holder: AccordionListHolder,
        position: Int
    ) {
        val data = data[position]
        holder.bind(data)
    }

}


open class LessonsAccordionAdapter(
    val data: List<LessonAccordionData>,
    val accordionCollapsedAction: () -> Unit,
    val accordionExpandedAction: () -> Unit,
) : RecyclerView.Adapter<LessonsAccordionAdapter.AccordionListHolder>() {

    private val expandedItemIndex = 0

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AccordionListHolder {
        val itemBinding = AccordionViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AccordionListHolder(
            itemBinding,
            data = data,
            accordionCollapsedAction = accordionCollapsedAction,
            accordionExpandedAction = accordionExpandedAction,
            expandedItemIndex
        )
    }

    class AccordionListHolder(
        val itemBinding: AccordionViewBinding,
        val data: List<LessonAccordionData>,
        val accordionCollapsedAction: () -> Unit,
        val accordionExpandedAction: () -> Unit,
        val expandedItemIndex: Int,
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(data: LessonAccordionData) {
            with(itemBinding) {
                this.tvCategory.text = data.accordionTittle
                this.accordionsRecyclerView.layoutManager = LinearLayoutManager(this.root.context)
                this.accordionsRecyclerView.adapter = data.accordionListAdapter
                this.ivArrow.setOnClickListener {
                    if (data.expanded){
                        collapseView(data)
                    } else expandView(data)
                }
            }
        }

        private fun expandView(data: LessonAccordionData) {
            with(itemBinding) {
                ivArrow.rotation = 180F
                accordionsRecyclerView.gone()
                data.expanded = !data.expanded
            }

        }

        private fun collapseView(data: LessonAccordionData) {
            with(itemBinding) {
                ivArrow.rotation = 0F
                accordionsRecyclerView.gone()
                data.expanded = !data.expanded
            }

        }

    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(
        holder: AccordionListHolder,
        position: Int
    ) {
        val data = data[position]
        holder.bind(data)
    }

}
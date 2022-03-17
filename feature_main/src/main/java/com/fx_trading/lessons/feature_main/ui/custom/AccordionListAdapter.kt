package com.fx_trading.lessons.feature_main.ui.custom

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fx_trading.lessons.domain.entities.lesson.Lesson
import com.fx_trading.lessons.feature_main.ui.lessons.LessonsAdapter
import com.fx_trading.lessons.features.databinding.AccordionViewBinding
import com.fx_trading.lessons.utils.utils.gone
import com.fx_trading.lessons.utils.utils.visible


open class BaseAccordionListAdapter<A : RecyclerView.Adapter<*>>(
    val dataList: List<AccordionData<A>>,
    val accordionCollapsedAction: () -> Unit,
    val accordionExpandedAction: () -> Unit
) : RecyclerView.Adapter<BaseAccordionListAdapter<A>.AccordionListHolder>() {

    val adaptersRecyclerView = mutableListOf<Pair<RecyclerView.Adapter<*>, RecyclerView?>>()


    companion object{
        var expandedData: AccordionData<*>? = null
    }

    init {
        dataList.forEach {
            adaptersRecyclerView.add(
                it.accordionListAdapter to it.recyclerView
            )
        }
    }

    // Определить адаптеры для обновления, обновить в них дату и пересетить
    @RequiresApi(Build.VERSION_CODES.N)
    fun <A : RecyclerView.Adapter<*>, T> updateData(data: T) {
        if (data is Lesson) {
            val adaptersForUpdate = mutableListOf<Pair<LessonsAdapter, RecyclerView?>>()
            adaptersRecyclerView.forEach {
                val adapterFromAdapters = it.first as LessonsAdapter
                if (adapterFromAdapters.data.map { it.id }.contains((data as Lesson).id)) {
                    adaptersForUpdate.add(adapterFromAdapters to it.second)
                }
            }
            adaptersForUpdate.forEach { pair ->
                val lastData = pair.first.data.toMutableList()
                val newData = lastData.filter { it.id != data.id }.toMutableList()
                newData.add(data)
                 val newAdapter = LessonsAdapter(
                     newData,
                     pair.first.openLessonAction,
                     pair.first.likeLessonAction,
                     pair.first.completedLessonIDs
                 )
                (pair.second as RecyclerView).adapter = newAdapter
                adaptersRecyclerView.remove(pair)
                adaptersRecyclerView.add(newAdapter to pair.second)
                dataList.firstOrNull { it.accordionTittle == data.title }?.accordionListAdapter = newAdapter
            }

        }

    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AccordionListHolder {
        val itemBinding = AccordionViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AccordionListHolder(
            itemBinding,
            data = dataList,
            accordionCollapsedAction = accordionCollapsedAction,
            accordionExpandedAction = accordionExpandedAction,
        )
    }

    inner class AccordionListHolder(
        val itemBinding: AccordionViewBinding,
        val data: List<AccordionData<A>>,
        val accordionCollapsedAction: () -> Unit,
        val accordionExpandedAction: () -> Unit
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(data: AccordionData<A>) {
            with(itemBinding) {
                tvCategory.text = data.accordionTittle
                data.recyclerView = accordionsRecyclerView
                accordionsRecyclerView.layoutManager = LinearLayoutManager(this.root.context)
                accordionsRecyclerView.adapter = data.accordionListAdapter

                if (expandedData == data) {
                    expandView()
                } else collapseView()

                ivArrow.setOnClickListener {
                    if (expandedData == data) {
                        collapseView()
                    } else {
                        expandedData = data
                        expandView()
                    }
                }

            }
        }

        private fun expandView() {
            with(itemBinding) {
                ivArrow.rotation = 180F
                accordionsRecyclerView.visible()
                accordionExpandedAction.invoke()
            }

        }

        private fun collapseView() {
            with(itemBinding) {
                ivArrow.rotation = 0F
                accordionsRecyclerView.gone()
                accordionCollapsedAction.invoke()
            }
        }

    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: AccordionListHolder, position: Int) {
        val data = dataList[position]
        holder.bind(data)
    }
}

/*inline fun <reified T,reified V> updateData(data: T, adapter: V) {
      if (adapter is LessonsAdapter){
          adapter as
          adapters.forEach {  adapters ->
              if (adapters.data.map { it.id }.contains(data.id)){
                  val lessonsFromAdapter: MutableList<Lesson> = lessonAdapter.data.toMutableList()
                  val lessonForRemove = lessonsFromAdapter.firstOrNull { it.id == data.id }
                  if (lessonForRemove != null){
                      lessonsFromAdapter.remove(lessonForRemove)
                      lessonsFromAdapter.add(data)
                      val newAdapter = LessonsAdapter(lessonsFromAdapter, lessonAdapter.openLessonAction, lessonAdapter.likeLessonAction, lessonAdapter.completedLessonIDs)
                      this.accordionsRecyclerView.layoutManager = LinearLayoutManager(this.root.context)
                      accordionsRecyclerView.adapter = BaseAccordionAdapter(data = lessonsFromAdapter, accCollapsedAction, accordionExpandedAction)
                      adapters.remove(lessonAdapter)

                  }
              }
          }
      }

  }*/
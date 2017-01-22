package com.adammcneilly.mobileleaguehacking.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.adammcneilly.mobileleaguehacking.R
import com.adammcneilly.mobileleaguehacking.models.Event
import java.util.*

/**
 * Displays a list of Events.
 *
 * Created by adam.mcneilly on 1/21/17.
 */
open class EventAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var fullList: MutableList<Any> = ArrayList()

    constructor(items: List<Event>): this() {
        val sectionMap = items.groupBy(Event::getDayDisplay)
        sectionMap.forEach {
            fullList.add(it.key)
            fullList.addAll(it.value)
        }
    }

    override fun getItemCount(): Int {
        return fullList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (fullList[position] is String) SECTION else EVENT
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        when (getItemViewType(position)) {
            SECTION -> {
                (holder as? SectionViewHolder)?.bindItem(fullList[position] as String)
            }
            EVENT -> {
                (holder as? EventViewHolder)?.bindItem(fullList[position] as Event)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val context = parent?.context

        when (viewType) {
            SECTION -> {
                val view = LayoutInflater.from(context).inflate(R.layout.list_item_textview, parent, false)
                return SectionViewHolder(view)
            }
            else /*EVENT*/ -> {
                val view = LayoutInflater.from(context).inflate(R.layout.list_item_event, parent, false)
                return EventViewHolder(view)
            }
        }
    }

    open class SectionViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private var textView: TextView? = null

        init {
            textView = view.findViewById(R.id.text_view) as? TextView
        }

        fun bindItem(item: String) {
            textView?.text = item
        }
    }

    open class EventViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private var eventName: TextView? = null
        private var eventTime: TextView? = null

        init {
            eventName = view.findViewById(R.id.event_name) as? TextView
            eventTime = view.findViewById(R.id.event_time) as? TextView
        }

        fun bindItem(item: Event) {
            eventName?.text = item.name
            eventTime?.text = item.getStartTimeDisplay()
        }
    }

    companion object {
        private val SECTION = 0
        private val EVENT = 1
    }
}
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
open class EventAdapter(): RecyclerView.Adapter<EventAdapter.EventViewHolder>() {
    var items: List<Event> = ArrayList()

    constructor(items: List<Event>): this() {
        this.items = items
    }

    fun swapItems(items: List<Event>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: EventViewHolder?, position: Int) {
        holder?.bindEvent(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): EventViewHolder {
        val context = parent?.context
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_event, parent, false)
        return EventViewHolder(view)
    }

    open class EventViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private var eventName: TextView? = null

        init {
            eventName = view.findViewById(R.id.event_name) as? TextView
        }

        fun bindEvent(event: Event) {
            eventName?.text = event.name
        }
    }
}
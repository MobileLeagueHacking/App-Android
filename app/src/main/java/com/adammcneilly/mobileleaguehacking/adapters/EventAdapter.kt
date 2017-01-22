package com.adammcneilly.mobileleaguehacking.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.adammcneilly.mobileleaguehacking.R
import com.adammcneilly.mobileleaguehacking.models.Event

/**
 * Displays a list of Events.
 *
 * Created by adam.mcneilly on 1/21/17.
 */
open class EventAdapter: BaseAdapter<Event, EventAdapter.EventViewHolder> {

    constructor(): super()

    constructor(items: List<Event>): super(items)

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): EventViewHolder {
        val context = parent?.context
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_event, parent, false)
        return EventViewHolder(view)
    }

    open class EventViewHolder(view: View): BaseViewHolder<Event>(view) {
        private var eventName: TextView? = null

        init {
            eventName = view.findViewById(R.id.event_name) as? TextView
        }

        override fun bindItem(item: Event) {
            eventName?.text = item.name
        }
    }
}
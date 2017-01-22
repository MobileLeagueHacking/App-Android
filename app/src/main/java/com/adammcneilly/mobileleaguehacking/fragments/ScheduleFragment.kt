package com.adammcneilly.mobileleaguehacking.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adammcneilly.mobileleaguehacking.DividerItemDecoration
import com.adammcneilly.mobileleaguehacking.R
import com.adammcneilly.mobileleaguehacking.adapters.EventAdapter
import com.adammcneilly.mobileleaguehacking.models.Event
import java.util.*

/**
 * Fragment that displays a list of events in the app.
 *
 * Created by adam.mcneilly on 1/21/17.
 */
open class ScheduleFragment : Fragment() {
    private var events: List<Event> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        events = arguments.getParcelableArrayList(EVENTS)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_events, container, false)

        val adapter = EventAdapter(events)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val recyclerView = view?.findViewById(R.id.event_list) as RecyclerView
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))

        return view
    }

    companion object {
        private val EVENTS = "Events"

        fun newInstance(events: List<Event>): ScheduleFragment {
            val bundle = Bundle()
            bundle.putParcelableArrayList(EVENTS, ArrayList(events))

            val fragment = ScheduleFragment()
            fragment.arguments = bundle

            return fragment
        }
    }
}
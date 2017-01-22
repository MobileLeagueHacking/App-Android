package com.adammcneilly.mobileleaguehacking.fragments

import android.os.Bundle
import com.adammcneilly.mobileleaguehacking.adapters.BaseAdapter
import com.adammcneilly.mobileleaguehacking.adapters.EventAdapter
import com.adammcneilly.mobileleaguehacking.models.Event
import java.util.*

/**
 * Fragment that displays a list of events in the app.
 *
 * Created by adam.mcneilly on 1/21/17.
 */
open class ScheduleFragment : BaseListFragment<Event>() {

    override fun getAdapter(): BaseAdapter<Event, *> {
        return EventAdapter(items)
    }

    companion object {
        fun newInstance(events: List<Event>): ScheduleFragment {
            val bundle = Bundle()
            bundle.putParcelableArrayList(ITEMS, ArrayList(events))

            val fragment = ScheduleFragment()
            fragment.arguments = bundle

            return fragment
        }
    }
}
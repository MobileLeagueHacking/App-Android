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
import com.adammcneilly.mobileleaguehacking.adapters.SponsorAdapter
import com.adammcneilly.mobileleaguehacking.models.Sponsor
import java.util.*

/**
 * Fragment that displays a list of sponsors in the app.
 *
 * Created by adam.mcneilly on 1/21/17.
 */
open class SponsorFragment: Fragment() {
    private var sponsors: List<Sponsor> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sponsors = arguments.getParcelableArrayList(SPONSORS)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_sponsors, container, false)

        val adapter = SponsorAdapter(sponsors)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val recyclerView = view?.findViewById(R.id.sponsor_list) as RecyclerView
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))

        return view
    }

    companion object {
        private val SPONSORS = "Sponsors"

        fun newInstance(sponsors: List<Sponsor>): SponsorFragment {
            val bundle = Bundle()
            bundle.putParcelableArrayList(SPONSORS, ArrayList(sponsors))

            val fragment = SponsorFragment()
            fragment.arguments = bundle

            return fragment
        }
    }
}
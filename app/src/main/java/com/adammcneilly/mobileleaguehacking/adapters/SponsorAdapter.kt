package com.adammcneilly.mobileleaguehacking.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.adammcneilly.mobileleaguehacking.R
import com.adammcneilly.mobileleaguehacking.models.Sponsor

/**
 * Displays a list of sponsors.
 *
 * Created by adam.mcneilly on 1/21/17.
 */
open class SponsorAdapter: BaseAdapter<Sponsor, SponsorAdapter.SponsorViewHolder> {
    constructor(): super()

    constructor(items: List<Sponsor>): super(items)

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SponsorViewHolder {
        val context = parent?.context
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_sponsor, parent, false)
        return SponsorViewHolder(view)
    }

    open class SponsorViewHolder(view: View): BaseAdapter.BaseViewHolder<Sponsor>(view) {
        private var sponsorName: TextView? = null

        init {
            sponsorName = view.findViewById(R.id.sponsor_name) as? TextView
        }

        override fun bindItem(item: Sponsor) {
            sponsorName?.text = item.name
        }
    }
}
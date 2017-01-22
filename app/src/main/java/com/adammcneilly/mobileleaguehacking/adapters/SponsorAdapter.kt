package com.adammcneilly.mobileleaguehacking.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.adammcneilly.mobileleaguehacking.R
import com.adammcneilly.mobileleaguehacking.models.Sponsor
import java.util.*

/**
 * Displays a list of sponsors.
 *
 * Created by adam.mcneilly on 1/21/17.
 */
open class SponsorAdapter(): RecyclerView.Adapter<SponsorAdapter.SponsorViewHolder>() {
    var items: List<Sponsor> = ArrayList()

    constructor(items: List<Sponsor>): this() {
        this.items = items
    }

    fun swapItems(items: List<Sponsor>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: SponsorViewHolder?, position: Int) {
        holder?.bindSponsor(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SponsorViewHolder {
        val context = parent?.context
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_sponsor, parent, false)
        return SponsorViewHolder(view)
    }

    open class SponsorViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private var sponsorName: TextView? = null

        init {
            sponsorName = view.findViewById(R.id.sponsor_name) as? TextView
        }

        fun bindSponsor(sponsor: Sponsor) {
            sponsorName?.text = sponsor.name
        }
    }
}
package com.adammcneilly.mobileleaguehacking.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.adammcneilly.mobileleaguehacking.R
import com.adammcneilly.mobileleaguehacking.models.Hackathon
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import java.util.*
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target


/**
 * Displays a list of Hackathons in a Recyclerview.
 *
 * Created by adam.mcneilly on 1/21/17.
 */
open class HackathonAdapter(): RecyclerView.Adapter<HackathonAdapter.HackathonViewHolder>() {
    var items: List<Hackathon> = ArrayList()

    constructor(items: List<Hackathon>): this() {
        this.items = items
    }

    fun swapItems(items: List<Hackathon>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: HackathonViewHolder?, position: Int) {
        holder?.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): HackathonViewHolder {
        val context = parent?.context
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_hackathon, parent, false)
        return HackathonViewHolder(view)
    }

    open class HackathonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var hackathonImage: ImageView? = null
        private var hackathonName: TextView? = null
        private var hackathonDate: TextView? = null
        private var hackathonLocation: TextView? = null

        init {
            hackathonImage = view.findViewById(R.id.hackathon_image) as? ImageView
            hackathonName = view.findViewById(R.id.hackathon_name) as? TextView
            hackathonDate = view.findViewById(R.id.hackathon_date) as? TextView
            hackathonLocation = view.findViewById(R.id.hackathon_location) as? TextView
        }

        fun bind(hackathon: Hackathon) {
            Glide.with(itemView.context)
                    .load(hackathon.mainImageUrl)
                    .error(R.drawable.noise)
                    .into(hackathonImage)
            hackathonName?.text = hackathon.eventName
            hackathonDate?.text = hackathon.date
            hackathonLocation?.text = hackathon.location
        }
    }
}
package com.adammcneilly.mobileleaguehacking.adapters

import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.adammcneilly.mobileleaguehacking.R
import com.adammcneilly.mobileleaguehacking.activities.HackathonEventActivity
import com.adammcneilly.mobileleaguehacking.models.Hackathon
import com.bumptech.glide.Glide
import java.util.*


/**
 * Displays a list of Hackathons in a Recyclerview.
 *
 * Created by adam.mcneilly on 1/21/17.
 */
open class HackathonAdapter(): RecyclerView.Adapter<HackathonAdapter.HackathonViewHolder>() {
    /**
     * The data source of Hackathons to be displayed.
     */
    var items: List<Hackathon> = ArrayList()

    constructor(items: List<Hackathon>): this() {
        this.items = items
    }

    /**
     * Replaces the current data set with a new one.
     */
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

    class HackathonViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener{
        private var hackathonImage: ImageView? = null
        private var hackathonLogo: ImageView? = null
        private var hackathonName: TextView? = null
        private var hackathonDate: TextView? = null
        private var hackathonLocation: TextView? = null
        private var hackathon: Hackathon? = null

        init {
            itemView.setOnClickListener(this)

            hackathonImage = view.findViewById(R.id.hackathon_image) as? ImageView
            hackathonLogo = view.findViewById(R.id.hackathon_logo) as? ImageView
            hackathonName = view.findViewById(R.id.hackathon_name) as? TextView
            hackathonDate = view.findViewById(R.id.hackathon_date) as? TextView
            hackathonLocation = view.findViewById(R.id.hackathon_location) as? TextView
        }

        fun bind(hackathon: Hackathon) {
            this.hackathon = hackathon
            Glide.with(itemView.context)
                    .load(hackathon.imageURL)
                    .error(R.drawable.noise)
                    .into(hackathonImage)
            Glide.with(itemView.context)
                    .load(hackathon.logoURL)
                    .error(R.drawable.noise)
                    .into(hackathonLogo)
            hackathonName?.text = hackathon.name
            hackathonDate?.text = hackathon.date.toUpperCase()
            hackathonLocation?.text = hackathon.location
        }

        override fun onClick(v: View?) {
            if (hackathon != null) {
                val context = itemView?.context
                val intent = Intent(context, HackathonEventActivity::class.java)
                intent.putExtra(HackathonEventActivity.HACKATHON, hackathon)
                context?.startActivity(intent)
            }
        }
    }
}
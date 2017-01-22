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
import com.adammcneilly.mobileleaguehacking.models.*
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

        var testResponse = HackathonTemplateResponse()

        init {
            itemView.setOnClickListener(this)

            hackathonImage = view.findViewById(R.id.hackathon_image) as? ImageView
            hackathonLogo = view.findViewById(R.id.hackathon_logo) as? ImageView
            hackathonName = view.findViewById(R.id.hackathon_name) as? TextView
            hackathonDate = view.findViewById(R.id.hackathon_date) as? TextView
            hackathonLocation = view.findViewById(R.id.hackathon_location) as? TextView

            //TODO: Remove
            testResponse.hackathon.name = "HackMTY"
            testResponse.hackathon.logoURL = "https://s3.amazonaws.com/assets.mlh.io/events/logos/000/000/392/thumb/e722cf7c0b4d-hackmtyMLH_100x100.png?1467906270"
            testResponse.hackathon.date = "August 27th - 28th"

            val helloWorld = Sponsor()
            helloWorld.name = "HelloWorld"

            val ics = Sponsor()
            ics.name = "Intrepid Control Systems"
            testResponse.sponsors = listOf(helloWorld, ics)

            val noLight = Event()
            noLight.name = "!Light"

            val techTalk = Event()
            techTalk.name = "Tech Talk"
            testResponse.schedule = listOf(noLight, techTalk)
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
                //TODO: Make call for hackathon name and get response

                launchTemplate(testResponse)
            }
        }

        private fun launchApp(response: HackathonAppResponse) {
            val intent = itemView.context.packageManager.getLaunchIntentForPackage(response.packageId)
            if (intent != null) {
                itemView.context.startActivity(intent)
            } else {
                val market = Intent(Intent.ACTION_VIEW)
                market.data = Uri.parse("https://play.google.com/store/apps/details?id=" + response.packageId)
                itemView.context.startActivity(market)
            }
        }

        private fun launchWebsite(response: HackathonWebResponse) {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(response.url)
            itemView.context.startActivity(intent)
        }

        private fun launchTemplate(response: HackathonTemplateResponse) {
            val intent = Intent(itemView.context, HackathonEventActivity::class.java)
            intent.putExtra(HackathonEventActivity.TEMPLATE, response)
            itemView.context.startActivity(intent)
        }
    }
}
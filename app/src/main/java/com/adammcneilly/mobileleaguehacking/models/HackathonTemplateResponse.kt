package com.adammcneilly.mobileleaguehacking.models

import android.os.Parcel
import android.os.Parcelable
import com.adammcneilly.mobileleaguehacking.utils.creator
import java.util.*

/**
 * Response from the service indicating we should use the template for the mobile app.
 *
 * Created by adam.mcneilly on 1/21/17.
 */
open class HackathonTemplateResponse() : Parcelable {
    /**
     * The Hackathon we are displaying.
     */
    var hackathon = Hackathon()

    /**
     * The events that are taking place during the event.
     */
    var schedule: List<Event> = ArrayList()

    /**
     * The prizes that are available during the event.
     */
    var prizes: List<Prize> = ArrayList()

    /**
     * The sponsors that are supporting the event.
     */
    var sponsors: List<Sponsor> = ArrayList()

    constructor(typeResponse: HackathonTypeResponse): this() {
        schedule = typeResponse.schedule
        prizes = typeResponse.prizes
        sponsors = typeResponse.sponsors
    }

    constructor(source: Parcel) : this() {
        hackathon = source.readParcelable(Hackathon::class.java.classLoader)
        source.readList(schedule, Event::class.java.classLoader)
        source.readList(prizes, Prize::class.java.classLoader)
        source.readList(sponsors, Sponsor::class.java.classLoader)
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeParcelable(hackathon, flags)
        dest?.writeList(schedule)
        dest?.writeList(prizes)
        dest?.writeList(sponsors)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField val CREATOR = creator(::HackathonTemplateResponse)
    }
}
package com.adammcneilly.mobileleaguehacking.models

import android.os.Parcel
import android.os.Parcelable
import com.adammcneilly.mobileleaguehacking.utils.creator

/**
 * Represents an event on the schedule.
 *
 * Created by adam.mcneilly on 1/21/17.
 */
open class Event(): Parcelable {
    /**
     * The name of the event.
     */
    var name = ""

    /**
     * The location or classroom for the event
     */
    var location = ""

    /**
     * The starting time for the event.
     */
    var startTime = ""

    constructor(source: Parcel): this() {
        name = source.readString()
        location = source.readString()
        startTime = source.readString()
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(name)
        dest?.writeString(location)
        dest?.writeString(startTime)
    }

    override fun describeContents(): Int {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        @JvmField val CREATOR = creator(::Event)
    }
}
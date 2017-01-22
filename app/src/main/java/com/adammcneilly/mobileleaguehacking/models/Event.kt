package com.adammcneilly.mobileleaguehacking.models

import android.os.Parcel
import android.os.Parcelable
import com.adammcneilly.mobileleaguehacking.utils.creator
import java.text.SimpleDateFormat
import java.util.*

/**
 * Represents an event on the schedule.
 *
 * Created by adam.mcneilly on 1/21/17.
 */
open class Event(): BaseModel() {
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
    var time = ""

    constructor(source: Parcel): this() {
        name = source.readString()
        location = source.readString()
        time = source.readString()
    }

    fun getStartTimeDisplay(): String {
        if (time.isNotEmpty()) {
            try{
                val date = timeFormat.parse(time)
                return timeDisplay.format(date)
            } catch (e: Exception) {
                return ""
            }
        }

        return ""
    }

    fun getDayDisplay(): String {
        if (time.isNotEmpty()) {
            try {
                val date = timeFormat.parse(time)
                return dayDisplay.format(date)
            } catch (e: Exception) {
                return ""
            }
        }

        return ""
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(name)
        dest?.writeString(location)
        dest?.writeString(time)
    }

    companion object {
        @JvmField val CREATOR = creator(::Event)

        private val timeFormat = SimpleDateFormat("yyyy-mm-dd'T'HH:mm", Locale.getDefault())
        private val timeDisplay = SimpleDateFormat("HH:mm", Locale.getDefault())
        private val dayDisplay = SimpleDateFormat("E, MMM dd", Locale.getDefault())

        //TODO: Remove
        fun getSampleEvents(): List<Event> {
            val openingCeremony = Event()
            openingCeremony.name = "Opening Ceremony"
            openingCeremony.time = "2017-01-20T21:00"
            openingCeremony.location = "B115"

            val noLight = Event()
            noLight.name = "!Light"
            noLight.time = "2017-01-21T22:30"
            noLight.location = "B106"

            val expo = Event()
            expo.name = "Expo"
            expo.time = "2017-01-22T13:00"
            expo.location = "B Hallway"

            val closingCeremony = Event()
            closingCeremony.name = "Closing Ceremony"
            closingCeremony.time = "2017-01-22T15:00"
            closingCeremony.location = "B115"

            return listOf(openingCeremony, noLight, expo, closingCeremony)
        }
    }
}
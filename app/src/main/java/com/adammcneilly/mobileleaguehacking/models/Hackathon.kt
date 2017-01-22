package com.adammcneilly.mobileleaguehacking.models

import android.os.Parcel
import android.os.Parcelable
import com.adammcneilly.mobileleaguehacking.utils.creator
import java.text.SimpleDateFormat
import java.util.*

/**
 * Represents a hackathon event.
 *
 * Created by adam.mcneilly on 1/21/17.
 */
open class Hackathon() : Parcelable {
    /**
     * Formatter to convert a string into a date.
     */
    val dateFormatter = SimpleDateFormat("yyyy-mm-dd", Locale.getDefault())

    /**
     * The URL to the main hackathon image.
     */
    var imageURL = ""

    /**
     * The URL to the smaller hackathon image that sits in front.
     */
    var logoURL = ""

    /**
     * The title of the event.
     */
    var name = ""

    /**
     * The date range of the event, given in "Month day - day"
     */
    var date = ""

    /**
     * The URL to the event's website.
     */
    var hackURL = ""

    /**
     * The city and state of the event, presented as "City,State"
     */
    var location = ""
            // Since the API returns city,State without a space, let's add one ourselves.
        get() = field.replace(",", ", ")

    /**
     * The first date of the event.
     */
    var startDate = ""

    /**
     * The laste date of the event.
     */
    var endDate = ""

    /**
     * URL to the hackathon's map.
     */
    var mapUrl = ""

    constructor(source: Parcel) : this() {
        this.imageURL = source.readString()
        this.logoURL = source.readString()
        this.name = source.readString()
        this.date = source.readString()
        this.hackURL = source.readString()
        this.location = source.readString()
        this.startDate = source.readString()
        this.endDate = source.readString()
        this.mapUrl = source.readString()
    }

    /**
     * Returns a Date object for the startDate string.
     */
    fun getStartDate(): Date {
        return dateFormatter.parse(startDate)
    }

    /**
     * Returns a Date object for the endDate string.
     */
    fun getEndDate(): Date {
        return dateFormatter.parse(endDate)
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(imageURL)
        dest?.writeString(logoURL)
        dest?.writeString(name)
        dest?.writeString(date)
        dest?.writeString(hackURL)
        dest?.writeString(location)
        dest?.writeString(startDate)
        dest?.writeString(endDate)
        dest?.writeString(mapUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField val CREATOR = creator(::Hackathon)
    }
}
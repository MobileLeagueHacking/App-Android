package com.adammcneilly.mobileleaguehacking.models

import android.os.Parcel
import android.os.Parcelable
import com.adammcneilly.mobileleaguehacking.utils.creator

/**
 * An award that someone can earn at the event.
 *
 * Created by adam.mcneilly on 1/21/17.
 */
open class Prize(): BaseModel() {
    /**
     * The description for the prize.
     */
    var description = ""

    /**
     * The monetary value or object for the prize.
     */
    var value = ""

    /**
     * The sponsor who is supporting the prize.
     */
    var sponsor = ""

    constructor(source: Parcel): this() {
        description = source.readString()
        value = source.readString()
        sponsor = source.readString()
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(description)
        dest?.writeString(value)
        dest?.writeString(sponsor)
    }

    companion object {
        @JvmField val CREATOR = creator(::Prize)
    }
}
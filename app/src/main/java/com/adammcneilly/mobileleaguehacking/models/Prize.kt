package com.adammcneilly.mobileleaguehacking.models

import android.os.Parcel
import android.os.Parcelable
import com.adammcneilly.mobileleaguehacking.utils.creator

/**
 * An award that someone can earn at the event.
 *
 * Created by adam.mcneilly on 1/21/17.
 */
open class Prize(): Parcelable {
    /**
     * The description for the prize.
     */
    var description = ""

    /**
     * The monetary value or object for the prize.
     */
    var value = ""

    constructor(source: Parcel): this() {
        description = source.readString()
        value = source.readString()
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(description)
        dest?.writeString(value)
    }

    override fun describeContents(): Int {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        @JvmField val CREATOR = creator(::Prize)
    }
}
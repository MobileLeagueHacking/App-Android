package com.adammcneilly.mobileleaguehacking.models

import android.os.Parcel
import android.os.Parcelable
import com.adammcneilly.mobileleaguehacking.utils.creator

/**
 * Represents an event sponsor.
 *
 * Created by adam.mcneilly on 1/21/17.
 */
open class Sponsor(): BaseModel() {
    /**
     * The name of the sponsors company.
     */
    var name = ""

    /**
     * The sponsor's logo
     */
    var logo = ""

    /**
     * The sponsors website
     */
    var url = ""

    constructor(source: Parcel): this() {
        name = source.readString()
        logo = source.readString()
        url = source.readString()
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(name)
        dest?.writeString(logo)
        dest?.writeString(url)
    }

    companion object {
        @JvmField val CREATOR = creator(::Sponsor)
    }
}
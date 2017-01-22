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
    var website = ""

    constructor(source: Parcel): this() {
        name = source.readString()
        logo = source.readString()
        website = source.readString()
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(name)
        dest?.writeString(logo)
        dest?.writeString(website)
    }

    companion object {
        @JvmField val CREATOR = creator(::Sponsor)

        //TODO: Remove
        fun getSampleSponsors(): List<Sponsor> {
            val ibm = Sponsor()
            ibm.name = "IBM"
            ibm.website = "http://ibm.com"

            val quickenLoans = Sponsor()
            quickenLoans.name = "Quicken Loans"
            quickenLoans.website = "http://quickenloans.com"

            val delphi = Sponsor()
            delphi.name = "Delphi"
            delphi.website = "http://delphi.com"

            return listOf(ibm, quickenLoans, delphi)
        }
    }
}
package com.adammcneilly.mobileleaguehacking.models

import android.os.Parcel
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

        //TODO: Remove
        fun getSamplePrizes(): List<Prize> {
            val firstPlace = Prize()
            firstPlace.value = "$1000 to split + $3000 SendGrid credits + 6 months of Loader.io + 1 year of Wolfram|Alpha Pro"
            firstPlace.description = "First Place"
            firstPlace.sponsor = "SpartaHack"

            val secondPlace = Prize()
            secondPlace.value = "$750 to split +$1560 in SendGrid credits + 1 year of Wolfram|Alpha Pro"
            secondPlace.description = "Second Place"
            secondPlace.sponsor = "SpartaHack"

            val thirdPlace = Prize()
            thirdPlace.value = "$500 to split + 1 year of Wolfram|Alpha Pro"
            thirdPlace.description = "Third Place"
            thirdPlace.sponsor = "SpartaHack"

            return listOf(firstPlace, secondPlace, thirdPlace)
        }
    }
}
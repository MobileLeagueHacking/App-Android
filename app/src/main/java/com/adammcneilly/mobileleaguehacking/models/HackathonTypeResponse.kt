package com.adammcneilly.mobileleaguehacking.models

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

/**
 * Response from the server telling us what kind of hackathon we are working with.
 *
 * Created by adam.mcneilly on 1/22/17.
 */
open class HackathonTypeResponse {

    @SerializedName("app_type")
    var type = WEB

    @SerializedName("android_app")
    var packageName = ""

    var schedule: List<Event> = ArrayList()

    var prizes: List<Prize> = ArrayList()

    var sponsors: List<Sponsor> = ArrayList()

    @SerializedName("custom_url")
    var customUrl = ""

    companion object {
        val APP = 0
        val TEMPLATE = 1
        val WEB = 2

        val APP_TYPE = "app_type"
    }
}
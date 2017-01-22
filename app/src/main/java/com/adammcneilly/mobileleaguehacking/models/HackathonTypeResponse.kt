package com.adammcneilly.mobileleaguehacking.models

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName
import java.lang.reflect.Type

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

    open class HackathonTypeResponseDeserializer: JsonDeserializer<HackathonTypeResponse> {
        override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): HackathonTypeResponse {
            val response = HackathonTypeResponse()

            if (json != null && json.isJsonObject) {
                val root = json.asJsonObject

                if (root.has(APP_TYPE) && root.get(APP_TYPE).isJsonPrimitive) {
                    response.type = root.get(APP_TYPE).asInt

                    when (response.type) {
                        APP -> {
                            if (root.has("android_app") && root.get("android_app").isJsonPrimitive) {
                                response.packageName = root.get("android_app").asString
                            }
                        }
                    }
                } else {
                    // WHAT?!
                }
            }

            return response
        }
    }

    companion object {
        val APP = 0
        val WEB = 1
        val TEMPLATE = 2

        val APP_TYPE = "app_type"
    }
}
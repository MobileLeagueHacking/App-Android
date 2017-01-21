package com.adammcneilly.mobileleaguehacking.models

/**
 * Represents a hackathon event.
 *
 * Created by adam.mcneilly on 1/21/17.
 */
open class Hackathon {
    var imageURL = ""
    var logoURL = ""
    var name = ""
    var date = ""
    var location = ""
        // Since the API returns city,State without a space, let's add one ourselves.
        get() = field.replace(",", ", ")
}
package com.adammcneilly.mobileleaguehacking.models

/**
 * Represents a hackathon event.
 *
 * Created by adam.mcneilly on 1/21/17.
 */
open class Hackathon {
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
}
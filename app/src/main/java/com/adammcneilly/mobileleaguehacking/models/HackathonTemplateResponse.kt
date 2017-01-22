package com.adammcneilly.mobileleaguehacking.models

import java.util.*

/**
 * Response from the service indicating we should use the template for the mobile app.
 *
 * Created by adam.mcneilly on 1/21/17.
 */
open class HackathonTemplateResponse {
    /**
     * The Hackathon we are displaying.
     */
    var hackathon = ""

    /**
     * The events that are taking place during the event.
     */
    var schedule: List<Event> = ArrayList()

    /**
     * The prizes that are available during the event.
     */
    var prizes: List<Prize> = ArrayList()

    /**
     * The sponsors that are supporting the event.
     */
    var sponsors: List<Sponsor> = ArrayList()
}
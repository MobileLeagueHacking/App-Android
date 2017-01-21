package com.adammcneilly.mobileleaguehacking.models

/**
 * A response indicating we should open an existing application id
 *
 * Created by adam.mcneilly on 1/21/17.
 */
open class HackathonAppResponse {
    /**
     * The name of the event.
     */
    var name = ""

    /**
     * The package id for the Google Play store.
     */
    var packageId = ""

    /**
     * The bundle id for the app in iTunes.
     */
    var bundleId = ""
}
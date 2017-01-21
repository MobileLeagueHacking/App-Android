package com.adammcneilly.mobileleaguehacking.utils

import java.util.*

/**
 * Extension methods for the date class.
 *
 * Created by adam.mcneilly on 1/21/17.
 */
fun Date.asCalendar(): Calendar {
    val calendar = Calendar.getInstance()
    calendar.time = this
    return calendar
}

fun Date.year(): Int {
    return asCalendar().get(Calendar.YEAR)
}

fun Date.month(): Int {
    return asCalendar().get(Calendar.MONTH)
}

fun Date.day(): Int {
    return asCalendar().get(Calendar.DAY_OF_MONTH)
}
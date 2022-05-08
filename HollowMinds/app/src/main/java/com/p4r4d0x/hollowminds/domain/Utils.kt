package com.p4r4d0x.hollowminds.domain

import java.util.concurrent.TimeUnit

object Utils {

    const val TIME_COUNTDOWN = 60000L
    const val COUNTDOWN_INTERVAL = 1000L
    private const val TIME_FORMAT = "%02d:%02d"

    fun Long.formatTime(): String = String.format(
        TIME_FORMAT,
        TimeUnit.MILLISECONDS.toMinutes(this),
        TimeUnit.MILLISECONDS.toSeconds(this) % 60
    )
}
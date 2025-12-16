package com.aaron.walkcore.util

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.ZoneId
import java.time.temporal.ChronoUnit
import java.util.Locale


// Return time for display
// Output example = 14:30
fun formatToDisplayTime(zdt: ZonedDateTime): String {
    val localZdt = zdt.withZoneSameInstant(ZoneId.systemDefault())
    return localZdt.format(DateTimeFormatter.ofPattern("HH:mm"))
}

// Output example = 26 Jan 2026, 12:00
fun formatToDisplayShortDateTime(zdt: ZonedDateTime): String {
    val localZdt = zdt.withZoneSameInstant(ZoneId.systemDefault())
    return localZdt.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"))
}

// Output example = 26 January 2026 12:00
fun formatToDisplayFullDateTime(zdt: ZonedDateTime): String {
    val localZdt = zdt.withZoneSameInstant(ZoneId.systemDefault())
    return localZdt.format(DateTimeFormatter.ofPattern("dd MMMM yyyy, HH:mm"))
}



fun formatToDisplayDateTimeRange(startTime: ZonedDateTime, endTime: ZonedDateTime): String {
    val localStart = startTime.withZoneSameInstant(ZoneId.systemDefault())
    val localEnd = endTime.withZoneSameInstant(ZoneId.systemDefault())

    val isSameDay = localStart.truncatedTo(ChronoUnit.DAYS)
        .isEqual(localEnd.truncatedTo(ChronoUnit.DAYS))

    val zoneId = ZoneId.systemDefault()
    val zoneName = zoneId.getDisplayName(java.time.format.TextStyle.SHORT, Locale.getDefault())

    val displayString: String

    // Output example = 12 June 2025, 09:00 - 10:00
    if (isSameDay) {
        val startPart = formatToDisplayFullDateTime(localStart)
        val timeEnd = localEnd.format(DateTimeFormatter.ofPattern("HH:mm"))

        displayString = "$startPart - $timeEnd"

        // Output example = 8 Jan 2025, 12:00 - 9 Jan 2025, 12:00
    } else {
        val startPart = formatToDisplayShortDateTime(localStart)
        val endPart = formatToDisplayShortDateTime(localEnd)

        displayString = "$startPart - $endPart"
    }

    return "$displayString $zoneName"
}
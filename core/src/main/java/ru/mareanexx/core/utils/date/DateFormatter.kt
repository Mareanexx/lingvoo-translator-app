package ru.mareanexx.core.utils.date

import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

object DateFormatter {
    private val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
    private val dateFormatter = DateTimeFormatter.ofPattern("d MMMM, yyyy")

    fun formatFavoriteCardTime(dateTime: OffsetDateTime): String {
        val now = OffsetDateTime.now(dateTime.offset)

        return if (dateTime.toLocalDate().isEqual(now.toLocalDate())) {
            "Today - ${dateTime.format(timeFormatter)}"
        } else {
            "${dateTime.format(dateFormatter)} - ${dateTime.format(timeFormatter)}"
        }
    }
}
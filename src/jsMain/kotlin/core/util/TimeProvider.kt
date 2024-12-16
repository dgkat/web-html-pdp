package core.util

import kotlinx.datetime.*

class TimeProvider(private val clock: Clock) {
    fun getCurrentTimeInMilliseconds(): Long {
        return clock.now().toEpochMilliseconds()
    }

    fun Long.toLocalDateTime(timeZone: TimeZone = TimeZone.currentSystemDefault()): LocalDateTime {
        val instant = Instant.fromEpochMilliseconds(this)
        return instant.toLocalDateTime(timeZone)
    }

    fun LocalDateTime.toEpochMillis(timeZone: TimeZone = TimeZone.currentSystemDefault()): Long {
        val instant = this.toInstant(timeZone)
        return instant.toEpochMilliseconds()
    }
}
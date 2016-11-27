package xyz.stavola.duration

import java.util.concurrent.TimeUnit

data class Duration(
        val value: Long,
        val unit: TimeUnit
) : Comparable<Duration> {

    operator fun inc() = copy(value = value + 1)
    operator fun dec() = copy(value = value - 1)

    operator fun plus(value: Int) = copy(value = this.value + value)
    operator fun minus(value: Int) = copy(value = this.value - value)
    operator fun times(value: Int) = copy(value = this.value * value)
    operator fun div(value: Int) = copy(value = this.value / value)

    operator fun plus(value: Long) = copy(value = this.value + value)
    operator fun minus(value: Long) = copy(value = this.value - value)
    operator fun times(value: Long) = copy(value = this.value * value)
    operator fun div(value: Long) = copy(value = this.value / value)

    operator fun plus(unit: TimeUnit) = this + unit(1)
    operator fun minus(unit: TimeUnit) = this - unit(1)
    operator fun times(unit: TimeUnit) = this * unit(1)
    operator fun div(unit: TimeUnit) = this / unit(1)

    operator fun plus(duration: Duration) = this + duration.convertTo(unit).value
    operator fun minus(duration: Duration) = this - duration.convertTo(unit).value
    operator fun times(duration: Duration) = this * duration.convertTo(unit).value
    operator fun div(duration: Duration) = this / duration.convertTo(unit).value

    operator fun contains(other: Duration): Boolean {
        return if (this.unit == other.unit) {
            this.value > other.value
        } else {
            this.unit > other.unit
        }
    }

    override operator fun compareTo(other: Duration): Int {
        val converted = other.convertTo(unit)

        return this.value.compareTo(converted.value)
    }

    fun convertTo(unit: TimeUnit) = Duration(unit.convert(value, this.unit), unit)
}

operator fun TimeUnit.invoke(value: Long) = Duration(value, this)
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

    operator fun rangeTo(duration: Duration) = DurationRange(this, duration.convertTo(unit))

    override operator fun compareTo(other: Duration): Int {
        val converted = other.convertTo(unit)

        return this.value.compareTo(converted.value)
    }

    fun convertTo(unit: TimeUnit) = Duration(unit.convert(value, this.unit), unit)
}

operator fun TimeUnit.invoke(value: Long) = Duration(value, this)

class DurationRange(
        override val endInclusive: Duration,
        override val start: Duration
) : DurationProgression(start, endInclusive, 1), ClosedRange<Duration> {
    override fun contains(value: Duration): Boolean = first <= value && value <= last

    override fun isEmpty(): Boolean = first > last

    override fun equals(other: Any?): Boolean =
            other is DurationRange && (isEmpty() && other.isEmpty() ||
                    first == other.first && last == other.last)

    override fun hashCode(): Int =
            if (isEmpty()) -1 else (31 * (first.value xor (first.value ushr 32)) + (last.value xor (last.value ushr 32))).toInt()

    override fun toString(): String = "$first..$last"

    companion object {
        public val EMPTY: DurationRange = DurationRange(TimeUnit.NANOSECONDS(1), TimeUnit.NANOSECONDS(0))
    }
}

open class DurationProgression
internal constructor(
        start: Duration,
        endInclusive: Duration,
        public val step: Long
) : Iterable<Duration> {
    init {
        if (step == 0L) throw IllegalArgumentException("Step must be non-zero")
    }

    public val first: Duration = start
    public val last: Duration by lazy {
        val converted = endInclusive.convertTo(start.unit)
        val lastTime = (start.value..converted.value step step).last

        Duration(lastTime, start.unit)
    }

    public open fun isEmpty(): Boolean = if (step > 0) first > last else first < last

    override fun iterator(): DurationIterator = DurationProgressionIterator(first, last, step)

    override fun equals(other: Any?) = other is DurationProgression &&
            (isEmpty() && other.isEmpty() || first == other.first && last == other.last && step == other.step)

    override fun hashCode() = if (isEmpty()) -1 else (31 * (31 * first.hashCode() + last.hashCode()) + step).toInt()
    override fun toString() = if (step > 0) "$first..$last step $step" else "$first downTo $last step ${-step}"

    companion object {
        public fun fromClosedRange(rangeStart: Duration, rangeEnd: Duration, step: Int) {
            return fromClosedRange(rangeStart, rangeEnd, step)
        }
    }
}

internal class DurationProgressionIterator(first: Duration, last: Duration, val step: Long) : DurationIterator() {
    private var next = first
    private val finalElement = last
    private var hasNext: Boolean = if (step > 0) first <= last else first >= last

    override fun hasNext(): Boolean = hasNext

    override fun nextDuration(): Duration {
        val value = next

        if (value == finalElement) {
            hasNext = false
        } else {
            next += step
        }

        return value
    }
}

public abstract class DurationIterator : Iterator<Duration> {
    override final fun next() = nextDuration()

    public abstract fun nextDuration(): Duration
}
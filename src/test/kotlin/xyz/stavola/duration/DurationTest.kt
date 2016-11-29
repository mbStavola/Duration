package xyz.stavola.duration

import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import java.util.concurrent.TimeUnit

class DurationTest {
    @Test fun `duration instantiation`() {
        val duration = Duration(1, TimeUnit.HOURS)
        val extension = TimeUnit.HOURS(1)

        assertEquals(duration, extension)
    }

    @Test fun `basic addition`() {
        val twoDays = TimeUnit.DAYS(2)

        val a = TimeUnit.DAYS(1)

        assertEquals("Duration + Integer", twoDays, a + 1)
        assertEquals("Duration + Long", twoDays, a + 1L)
        assertEquals("Duration + TimeUnit", twoDays, a + TimeUnit.DAYS)

        val b = TimeUnit.DAYS(2)

        assertEquals("Duration + Duration", TimeUnit.DAYS(3), a + b)
    }

    @Test fun `basic subtraction`() {
        val oneDay = TimeUnit.DAYS(1)

        val a = TimeUnit.DAYS(2)

        assertEquals("Duration - Integer", oneDay, a - 1)
        assertEquals("Duration - Long", oneDay, a - 1L)
        assertEquals("Duration - TimeUnit", oneDay, a - TimeUnit.DAYS)

        val b = TimeUnit.DAYS(2)

        assertEquals("Duration - Duration", TimeUnit.DAYS(0), a - b)
    }

    @Test fun `basic multiplication`() {
        val fourDays = TimeUnit.DAYS(4)

        val a = TimeUnit.DAYS(2)

        assertEquals("Duration * Integer", fourDays, a * 2)
        assertEquals("Duration * Long", fourDays, a * 2L)
        assertEquals("Duration * TimeUnit", a, a * TimeUnit.DAYS)

        val b = TimeUnit.DAYS(3)

        assertEquals("Duration * Duration", TimeUnit.DAYS(6), a * b)
    }

    @Test fun `basic division`() {
        val twoDays = TimeUnit.DAYS(2)

        val a = TimeUnit.DAYS(4)

        assertEquals("Duration / Integer", twoDays, a / 2)
        assertEquals("Duration / Long", twoDays, a / 2L)
        assertEquals("Duration / TimeUnit", a, a / TimeUnit.DAYS)

        val b = TimeUnit.DAYS(4)

        assertEquals("Duration / Duration", TimeUnit.DAYS(1), a / b)
    }

    @Test fun `use smallest units`() {
        val a = TimeUnit.DAYS(1)
        val b = TimeUnit.MINUTES(3)

        assertEquals(TimeUnit.MINUTES, (a + b).unit)
        assertEquals(TimeUnit.MINUTES, (b + a).unit)
    }

    @Test fun `increment and decrement`() {
        var a = TimeUnit.SECONDS(3)

        assertEquals(TimeUnit.SECONDS(4), ++a)
        assertEquals(TimeUnit.SECONDS(3), --a)
    }

    @Test fun `simple conversion`() {
        val a = TimeUnit.DAYS(1)

        assertEquals(TimeUnit.HOURS(24), a.convertTo(TimeUnit.HOURS))
    }

    @Test fun `duration equality`() {
        val oneDay = TimeUnit.DAYS(1)
        val twentyFourHours = TimeUnit.HOURS(24)

        assertTrue(oneDay == TimeUnit.DAYS(1))
        assertTrue(oneDay == twentyFourHours)
    }

    @Test fun `duration comparison`() {
        val fourDays = TimeUnit.DAYS(4)
        val sixDays = TimeUnit.DAYS(6)
        val oneHour = TimeUnit.HOURS(1)
        val twoMinutes = TimeUnit.MINUTES(2)

        assertTrue(sixDays > fourDays)
        assertTrue(fourDays >= TimeUnit.DAYS(4))
        assertTrue(fourDays > oneHour)
        assertTrue(twoMinutes < oneHour)
    }
}
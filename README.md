# Duration
This is a simple library to represent an immutable length of time, provided a quantity and unit.


## Documentation

Durations are simple to create:
```kotlin
// Direct instantiation
val singleSecond = Duration(1, TimeUnit.SECONDS)

// Extension invocation for TimeUnit
val tenDays = TimeUnit.DAYS(10)
```

They implement a variety of operators:
```kotlin
val fiveDays = TimeUnit.DAYS(5)
val twoHours = TimeUnit.HOURS(2)

// Standard arithmetic with durations
val added = twoHours + fiveDays
val subtracted = twoHours - fiveDays
val multiplied = twoHours * fiveDays
val divided = twoHours / fiveDays

// Standard arithmetic with values
val addedVal = twoHours + fiveDays
val subtractedVal = twoHours - fiveDays
val multipliedVal = twoHours * fiveDays
val dividedVal = twoHours / fiveDays

// Standard arithmetic with units
val addedUnit = twoHours + TimeUnit.DAY
val subtractedUnit = fiveDays - TimeUnit.HOUR
val multipliedUnit = twoHours * TimeUnit.DAY
val dividedUnit = fiveDays / TimeUnit.DAY

// Increment / Decrement also works
val sixDays = fiveDays++
val oneHour = twoHours--
```

You may also use a Duration in a range:
```kotlin
val twoDays = TimeUnit.DAYS(2)
val oneWeek = TimeUnit.DAYS(7)

// Range check
val inRange = TimeUnit.DAYS(3) in twoDays..oneWeek

// Loops + step
for (duration in twoDays..oneWeek step 2) {
    println(duration)
}
```
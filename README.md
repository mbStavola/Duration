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

// You can even do a range check
val isInRange = TimeUnit.SECONDS(10) in TimeUnit.DAYS(1) 
```
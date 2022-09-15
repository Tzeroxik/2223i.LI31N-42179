package pt.isel.date

import kotlin.time.Duration.Companion.milliseconds

object DateUtils {
    
    @Throws(IllegalArgumentException::class)
    fun validateYear(year: Int): Int =
        if (year > 0) year else throw IllegalArgumentException("Year (= $year) must be bigger than 0")
    
    @Throws(IllegalArgumentException::class)
    fun mapToMonthAndValidate(month: Int): Month =
        Month.tryGetMonth(month)
            ?: throw IllegalArgumentException("Month (= $month) must be between 1 and ${Month.values().size}")
    
    @Throws(IllegalArgumentException::class)
    fun validateDay(day: Int, month: Month, year: Int): Int =
        month.getNumberOfDays(year).let { upperBound ->
            if (day in 1..upperBound)
                day
            else
                throw IllegalArgumentException("Day (= $day) must be between 1 and $upperBound")
        }
    
    tailrec fun addDays(daysToAdd: Int, day: Int, month: Month, year: Int) : NaifDate {
        val numberOfDaysUntilNextMonth = month.getNumberOfDays(year) - day
        
        return if(daysToAdd < numberOfDaysUntilNextMonth) {
            NaifDate(day + daysToAdd, month, year)
        } else {
            val nextMonth = month.getNextMonth()
            val updatedYear = if(nextMonth == Month.January) year + 1 else year
            addDays(daysToAdd - numberOfDaysUntilNextMonth, 0, nextMonth, updatedYear)
        }
    }
    
    fun isLeapYear(year: Int): Boolean =
        year % 4 == 0 && year % 100 != 0 || year % 400 == 0
    
    private fun padValueAsString(i: Int, size: Int) =
        i.toString().padStart(size,'0')
    
    fun dayAsPaddedString(day: Int): String = padValueAsString(day, 2)
    fun monthAsPaddedString(month: Month): String = padValueAsString(month.getNumber(), 2)
    fun yearAsPaddedString(year: Int): String = padValueAsString(year, 4)
    
    
}
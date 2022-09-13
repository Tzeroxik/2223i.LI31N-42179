package pt.isel

import pt.isel.DateUtils.isLeapYear
import java.time.Year
import kotlin.jvm.Throws

class NaifDate(day: Int, month: Int, private val year: Int) {
    
    private val month : Month = ensureValidMonth(month)
    
    private val day = ensureValidDay(day, this.month, this.year)
    
    fun nextMonth(): Int =
        month.getNextMonth().getNumber()
    
    fun addDays(days: Int): NaifDate {
        return DateUtils.addDays(days, this.day, this.month, this.year)
    }
    
    @Throws(IllegalArgumentException::class)
    private fun ensureValidMonth(month: Int): Month =
        Month.getMonth(month)
            ?: throw IllegalArgumentException("Month must be between 1 and ${Month.values().size}")
    
    @Throws(IllegalArgumentException::class)
    private fun ensureValidDay(day: Int, month: Month, year: Int): Int {
        val upperBound = month.getNumberOfDays(year)
        return if (day in 1..upperBound) {
            day
        } else {
            throw IllegalArgumentException("Day must be between 1 and $upperBound")
        }
    }
    
    override fun toString(): String {
        return "$day-${month.getNumber()}-$year"
    }
    
    
}

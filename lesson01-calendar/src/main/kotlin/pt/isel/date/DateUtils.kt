package pt.isel.date

object DateUtils {
    
    fun isLeapYear(year: Int): Boolean =
        year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)
    
    @Throws(IllegalArgumentException::class)
    fun validateYear(year: Int) {
        if (year <= 0) {
            throw IllegalArgumentException("Year must be bigger than 0")
        }
    }
    
    @Throws(IllegalArgumentException::class)
    fun mapToMonthAndValidate(month: Int): Month =
        Month.tryGetMonth(month)
            ?: throw IllegalArgumentException("Month must be between 1 and ${Month.values().size}")
    
    @Throws(IllegalArgumentException::class)
    fun validateDay(day: Int, month: Month, year: Int) {
        val upperBound = month.getNumberOfDays(year)
        if (day !in 1..upperBound) {
            throw IllegalArgumentException("Day must be between 1 and $upperBound")
        }
    }
    
    tailrec fun addDays(daysToAdd: Int, day: Int, month: Month, year: Int) : NaifDate {
        val numberOfDaysUntilNextMonth =
            month.getNumberOfDays(year) - day
        
        return if(daysToAdd < numberOfDaysUntilNextMonth) {
            val updatedDays = day + daysToAdd
            NaifDate(updatedDays, month.getNumber(), year)
        } else {
            val updatedDaysToAdd =
                daysToAdd - numberOfDaysUntilNextMonth - 1
            
            val nextMonth = month.getNextMonth()
            
            val updatedYear =
                if (nextMonth == Month.January) {
                    year + 1
                } else {
                    year
                }
            
            addDays(updatedDaysToAdd,1, nextMonth, updatedYear)
        }
    }
    
    private fun padValueAsString(i: Int, size: Int) =
        i.toString().padStart(size,'0')
    
    fun dayAsPaddedString(day: Int): String = padValueAsString(day, 2)
    fun monthAsPaddedString(month: Month): String = padValueAsString(month.getNumber(), 2)
    fun yearAsPaddedString(year: Int): String = padValueAsString(year, 4)
    
    
}
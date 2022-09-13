package pt.isel

object DateUtils {
    fun isLeapYear(year: Int): Boolean =
        year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)
    
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
}
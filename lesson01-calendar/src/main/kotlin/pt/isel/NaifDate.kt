package pt.isel

class NaifDate(day: Int, month: Int, year: Int) {
    val year = ensureValidYear(year)
    val month : Month = ensureValidMonth(month)
    val day = ensureValidDay(day, this.month, this.year)
    
    fun nextMonth(): Int =
        month.getNextMonth().getNumber()
    
    fun addDays(days: Int): NaifDate {
        return DateUtils.addDays(days, this.day, this.month, this.year)
    }
    
    private fun ensureValidYear(year: Int) =
        if (year > 0) {
            year
        } else {
            throw IllegalArgumentException("Year must be bigger than 0")
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

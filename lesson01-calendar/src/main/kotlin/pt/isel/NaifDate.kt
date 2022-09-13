package pt.isel

class NaifDate(val day: Int, val month: Month, val year: Int) {
    
    init {
        DateUtils.validateYear(year)
        DateUtils.validateDay(day, month, year)
    }
    
    constructor(day: Int, month: Int, year: Int) : this(
        day,
        DateUtils.mapToMonthAndValidate(month),
        year
    )
    
    fun nextMonth(): Int =
        month.getNextMonth().getNumber()
    
    fun addDays(days: Int): NaifDate {
        return DateUtils.addDays(days, day, month, year)
    }
    
    override fun toString(): String {
        return "$day-${month.getNumber()}-$year"
    }
    
    
}

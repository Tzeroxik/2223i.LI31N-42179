package pt.isel.date

import pt.isel.date.DateUtils.isLeapYear

enum class Month(private val numberOfDays: Int,
                 private val numberOfDaysOnLeapYear: Int = numberOfDays) {
    
    January(31),
    February(28,29),
    March(31),
    April(30),
    May(31),
    June(30),
    July(31),
    August(31),
    September(30),
    October(31),
    November(30),
    December(31);
    
    fun getNumberOfDays(year: Int) : Int =
        if (isLeapYear(year))
            numberOfDaysOnLeapYear
        else
            numberOfDays
    
    fun getNumber() = ordinal + 1
    
    fun getNextMonth(): Month {
        val months = values()
        val nextIndex = (ordinal + 1) % months.size
        return months[nextIndex]
    }
    
    companion object {
        fun tryGetMonth(month: Int): Month? =
            if (isValidMonth(month)) {
                values()[month - 1]
            } else {
                null
            }
        
        fun isValidMonth(month: Int): Boolean =
            month > 0 && month <= values().size
    }
    
}
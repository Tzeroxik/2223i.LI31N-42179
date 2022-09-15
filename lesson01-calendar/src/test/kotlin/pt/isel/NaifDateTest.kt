package pt.isel

import pt.isel.date.Month
import pt.isel.date.NaifDate
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

class NaifDateTest {
    
    @Test fun `should get non null February instance`() {
        val month = Month.tryGetMonth(2)
        assertNotNull(month)
        assertEquals(Month.February, month)
    }
    @Test fun `should instance NaifDate with the values passed`() {
        val date = NaifDate(1, 2, 2020)
        val month = Month.tryGetMonth(2)
        
        assertEquals(1, date.day)
        assertEquals(month, date.month)
        assertEquals(2020, date.year)
    }
    
    @Test fun `should match the number of the month`() {
        arrayOf(
            11 to NaifDate(28, 10, 2022).nextMonth(),
            12 to NaifDate(28, 11, 2022).nextMonth(),
            1 to NaifDate(28, 12, 2022).nextMonth()
        )
        .forEach { (expected, actual) ->
            assertEquals(expected, actual)
        }
    }
    
    @Test fun `should match dates after adding days`() {
        arrayOf(
            NaifDate(10,12,2022) to NaifDate(28, 9, 2022).addDays(73),
            NaifDate(23,1,2023) to NaifDate(28, 9, 2022).addDays(117),
            NaifDate(9,2,2023) to NaifDate(28, 12, 2022).addDays(43)
        )
        .forEach { (expected, actual) ->
            assertEquals(expected, actual)
        }
    }
    
    @Test fun `date to string should have the correct format`() {
        arrayOf(
            "01-01-0001" to NaifDate(1,1,1).toString(),
            "10-12-2022" to NaifDate(10,12,2022).toString()
        )
        .forEach { (expected, actual) ->
            assertEquals(expected, actual)
        }
    }
    
    @Test fun `Naif instance should throw IllegalArgumentException for having an invalid year`(){
        assertFailsWith<IllegalArgumentException> {
            NaifDate(1,1,-29)
        }
    
        assertFailsWith<IllegalArgumentException> {
            NaifDate(1,1,0)
        }
    }
    @Test fun `Naif instance should throw IllegalArgumentException for having an invalid month`(){
        assertFailsWith<IllegalArgumentException> {
            NaifDate(1,0,2020)
        }
    
        assertFailsWith<IllegalArgumentException> {
            NaifDate(1,-1,2020)
        }
        
        assertFailsWith<IllegalArgumentException> {
            NaifDate(1,13,2020)
        }
    }
    @Test fun `Naif instance should throw IllegalArgumentException for having an invalid day`(){
        assertFailsWith<IllegalArgumentException> {
            NaifDate(0,1,2020)
        }
        
        assertFailsWith<IllegalArgumentException> {
            NaifDate(-1,1,-29)
        }
        
        assertFailsWith<IllegalArgumentException> {
            NaifDate(32,1,-29)
        }
        
        assertFailsWith<IllegalArgumentException> {
            NaifDate(29, Month.February,2022)
        }
        
        assertFailsWith<IllegalArgumentException> {
            NaifDate(30,Month.February,1988)
        }
    }
    
}

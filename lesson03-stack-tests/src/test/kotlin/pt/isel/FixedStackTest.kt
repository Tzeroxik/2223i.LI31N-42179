package pt.isel

import pt.isel.fixed.FixedStack
import kotlin.test.*

class FixedStackTest {
    @Test fun `new instance of FixedStack should be empty`() {
        val stk = FixedStack<String>()
        assertTrue(stk.isEmpty())
    }
    
    @Test fun `new instance of FixedStack not be empty`() {
        val stk = FixedStack("ISEL")
        assertFalse(stk.isEmpty())
    }
    
    @Test fun `last pushed item is the peeked item`() {
        val stk = FixedStack<String>().push("ISEL")
        
        assertFalse (stk.isEmpty())
        assertEquals("ISEL", stk.peek())
    }
    @Test fun `after popping Immutable with single item stays empty`() {
        val stk =
            FixedStack<String>().push("ISEL").pop()
        
        assertTrue(stk.isEmpty())
    }
    @Test fun `popping empty stack throws NoSuchElementException`() {
        val stk = FixedStack<String>()
        assertFailsWith<NoSuchElementException> {
            stk.pop()
        }
    }
    @Test fun `peeking empty stack throws NoSuchElementException`() {
        val stk = FixedStack<String>()
        assertFailsWith<NoSuchElementException> {
            stk.peek()
        }
    }
}

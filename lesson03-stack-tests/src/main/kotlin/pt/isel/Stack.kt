package pt.isel

import kotlin.jvm.Throws

interface Stack<T> {
    fun push(item: T) : Stack<T>
    
    @Throws(NoSuchElementException::class)
    fun peek(): T
    
    fun isEmpty(): Boolean
    
    @Throws(NoSuchElementException::class)
    fun pop(): Stack<T>
}

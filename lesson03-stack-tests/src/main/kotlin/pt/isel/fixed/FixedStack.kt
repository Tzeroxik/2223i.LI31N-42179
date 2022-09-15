package pt.isel.fixed

import pt.isel.Stack
import kotlin.jvm.Throws

data class FixedStack<T>(val head: Node<T>? = null) : Stack<T> {
    
    private constructor(item: T, next: Node<T>?) : this(Node(item, next))
    
    constructor(item: T) : this(Node(item, null))
    
    override fun push(item: T): Stack<T> = FixedStack(item, head)
    
    @Throws(NoSuchElementException::class)
    override fun peek(): T =
        failIfNull(head, Node<T>::value)
    
    @Throws(NoSuchElementException::class)
    override fun pop(): Stack<T> =
        failIfNull(head) { node -> FixedStack(node.next) }
    
    override fun isEmpty(): Boolean =
        head == null
    
    @Throws(NoSuchElementException::class)
    private inline fun <O> failIfNull(node: Node<T>?, mapper: (Node<T>) -> O) : O =
        mapper(node ?: throw NoSuchElementException())
    
}

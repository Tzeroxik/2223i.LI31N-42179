package pt.isel.fixed

import pt.isel.Stack

data class FixedStack<T>(val head: Node<T>? = null) : Stack<T> {
    
    private constructor(item: T, next: Node<T>?) : this(Node(item, next))
    
    constructor(item: T) : this(Node(item, null))
    
    override fun push(item: T): Stack<T> = FixedStack(item, head)
    
    override fun peek(): T =
        failIfNull(head, Node<T>::value)
    
    override fun pop(): Stack<T> =
        failIfNull(head) { node -> FixedStack(node.next) }
    
    override fun isEmpty(): Boolean =
        head == null
    
    private inline fun <O> failIfNull(node: Node<T>?, mapper: (Node<T>) -> O) : O =
        mapper(node ?: throw NoSuchElementException())
    
}

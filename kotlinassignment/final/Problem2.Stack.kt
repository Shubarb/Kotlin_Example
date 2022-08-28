package com.example.kotlinassignment.final

class Stack<T> {
    data class Node<T>(var item: T) {
        var next: Node<T>? = null
    }

    private var size: Int = 0
    private var first: Node<T>? = null

    fun getSize(): Int {
        return size
    }

    fun isEmpty(): Boolean {
        return first == null
    }

    fun add(item: T) {
        val oldNode = first
        first = Node(item)
        first?.next = oldNode
        size++
    }

    fun remove(): T {
        val value = first?.item ?: throw Exception("Stack empty")
        first = first?.next
        size--
        return value
    }
    fun peek(): T?
    {
        return first?.item
    }
}

fun main(args : Array<String>){
    var s = Stack<Int>()

    s.add(1)
    println("add element: ${s.peek()}")
    println("Size: ${s.getSize()}")

    s.add(2)
    println("add element: ${s.peek()}")
    println("Size: ${s.getSize()}")

    println()

    println("Remove element: ${s.peek()}")
    s.remove()
    println("Size: ${s.getSize()}")

    println("Remove element: ${s.peek()}")
    s.remove()
    println("Size: ${s.getSize()}")

    println("Remove element: ${s.peek()}")
}
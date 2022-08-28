package com.example.kotlinassignment.final

class Queue<T> {
    data class Node<T>(var item: T) {
        var next: Node<T>? = null
    }

    private var size: Int = 0
    private var first: Node<T>? = null
    private var last: Node<T>? = null

    fun getSize(): Int {
        return size
    }

    fun isEmpty(): Boolean {
        return first == null
    }

    fun add(item: T) {
        val oldNode = Node(item)
        if(isEmpty()){
            first = oldNode
        }else {
            last?.next = oldNode
        }
        size++
        last = oldNode
    }

    fun remove(): T {
        if(isEmpty()){
            println("Queue empty")
        }
        val value = first?.item ?: throw Exception("Queue empty")
        first = first?.next
        size--
        if(isEmpty()){
            last = null
        }
        return value
    }

    fun peek(): T?
    {
        if(isEmpty()){
            println("Queue Empty")
        }
        return first?.item
    }
}

fun main(args : Array<String>){
    var q = Queue<Char>()

    println("IsEmpty: ${q.isEmpty()}")
    q.add('A')
    println("IsEmpty: ${q.isEmpty()}")
    println("Add Element: ${q.peek()}")
    q.add('B')
    q.add('C')
    q.add('D')
    println("Size: ${q.getSize()}")
    println()

    println("Remove element: ${q.peek()}")
    q.remove()
    println("Size: ${q.getSize()}")

    println("Remove element: ${q.peek()}")
    q.remove()
    println("Size: ${q.getSize()}")

    println("Remove element: ${q.peek()}")
    q.remove()
    println("Size: ${q.getSize()}")

    println("Remove element: ${q.peek()}")
    q.remove()
    println("Size: ${q.getSize()}")

    println("Remove element: ${q.peek()}")
}
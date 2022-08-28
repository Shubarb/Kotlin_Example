package com.example.kotlinassignment.Assignment5

fun main() {
    var color = arrayOf("Red", "Blue", "Green","Black","White")
    var color2 = arrayOf("Pink", "Orange","Yellow", "Violet","Gray")
    var list: MutableList<String> = mutableListOf()
    var list2: MutableList<String> = mutableListOf()
    for(i in 0 until color.size){
        list.add(color[i])
        list2.add(color2[i])
    }
    println("list: $list")
    println("list2: $list2")

    println("------------------")
    println("Add list to list2:")
    list.addAll(list2)
    list2.clear()
    println(list)

    println("------------------")
    println("Caplock all Element:")
    println(list.map {x -> x.uppercase()})

    println("------------------")
    println("Delete Element4 - Element6:")

    if(list.size > 5){
        list.subList(3, 6).clear()
        println(list)
    }

    println("------------------")
    println("Reverse List:")
    list.reverse()
    println(list)
}
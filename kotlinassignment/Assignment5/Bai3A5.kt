package com.example.kotlinassignment.Assignment5

fun main() {

    var arr = ArrayList<Int>()
    print("Enter Number of Element: ")
    var n = readLine()!!.toInt()
    println("--------------------")
    for(i in 0 until n){
        print("Element ${i+1} : ")
        var a = readLine()!!.toInt()
        arr.add(a)
    }
    println("-------------")
    print("Enter the element you want to access: ")
    var b = readLine()!!.toInt()
    if(b>n){
        println("No element exists at this position")
    }
    else print("Element ${b}: ${arr[b-1]}")
}
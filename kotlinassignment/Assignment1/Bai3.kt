package com.example.kotlinassignment.Assignment1

fun main() {
    print("Enter the number of elements: ")
    val numberOfElements:Int = readLine()!!.toInt()
    val arr = IntArray(numberOfElements)
    for (i in 0 until numberOfElements) {
        print("a[${i}] = ")
        val elem:Int = readln()!!.toInt()
        arr[i] = elem
    }
    var bufVariable:Int
    for (i in 0 until numberOfElements-1){
        for(j in i+1 until numberOfElements){
            if(arr[i]>arr[j]){
                bufVariable = arr[i]
                arr[i] = arr[j]
                arr[j] = bufVariable
            }
        }
    }
    println("Sort up ascending: ")
    for (i in 0 until numberOfElements){
        print("${arr[i]} ")
    }
}
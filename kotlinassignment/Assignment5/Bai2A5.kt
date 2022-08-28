package com.example.kotlinassignment.Assignment5

fun main() {

    var arr = ArrayList<Int>()
    print("Enter Number of Element: ")
    var n = readLine()!!.toInt()
    println("--------------------")
    println("Input '100'")
    println("--------------------")
    for(i in 0 until n){
        print("Element ${i+1} : ")
        var a = readLine()!!.toInt()
        arr.add(a)
        if(arr[i] == 100){
            println("--------------------")
            print("Array: ")
            arr.forEach {
                print(it)
                print(" ")
            }
            break
        }
    }
}
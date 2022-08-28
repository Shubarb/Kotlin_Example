package com.example.kotlinassignment.Assignment5

open class Division<T> {
    fun elementSwap(element1: Double, element2: Double){
        if(element2 != 0.0){
            var n:Double = 0.0
            n = element1 / element2
            println(n)
        }else println("Invalid division")
    }
}

fun main(){

    var arr3 : Division<Double> = Division()
    println("--------------------")
    print("First Number: ")
    var f = readLine()!!.toDouble()
    print("Second Number: ")
    var s = readLine()!!.toDouble()
    println("-------------------")
    print("Result: ")
    arr3.elementSwap(f, s)
}
package com.example.kotlinassignment.Assignment2

fun sumMem(n:Int):Int{
    var arr = StringBuilder()
    var summem: Int =0
    var m:Int = n
    while (m!= 0){
        var NumbEnter = m % 10
        arr.append(NumbEnter.toString())
        arr.append("+")
        summem += NumbEnter
        m /= 10
    }
    arr.deleteAt(arr.length-1)
    arr.reverse()
    arr.append("=$summem")
    print(arr)
    return summem
}

fun main(){

    print("Nhap so: ")
    var n: Int = 0
    n = readLine()!!.toInt()
    sumMem(n)
}
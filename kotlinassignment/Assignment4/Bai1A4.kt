package com.example.kotlinassignment.Assignment4

interface ElementCount<T> {
    fun check(x: T):Boolean
}
fun <T> countElement(arr: ArrayList<T>, e:ElementCount<T>):Int{
    var count = 0
    for (i in arr) if (e.check(i)) count++
    return count
}
class countEven : ElementCount<Int> {
    override fun check(x: Int): Boolean {
        return x % 2 == 0
    }
}
class countOdd : ElementCount<Int> {
    override fun check(x: Int): Boolean {
        return x % 2 != 0
    }
}

fun main(){

    var arr = ArrayList<Int>()
    print("Enter Number of Element: ")
    var n = readLine()!!.toInt()
    println("--------------------")
    for(i in 0 until n){
        print("Element ${i+1} : ")
        var a = readLine()!!.toInt()
        arr.add(a)
    }
    println()
    val countEven = countElement<Int>(arr, countEven())
    println("Số phần tử chẵn là: $countEven")
    val countOdd = countElement<Int>(arr, countOdd())
    println("Số phần tử lẻ là: $countOdd")
    println("-------------------")
}




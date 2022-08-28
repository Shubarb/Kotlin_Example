package com.example.kotlinassignment.Assignment4

open class ElementMax<T> {
    fun elementMax(arr: ArrayList<T>, begin: Int, end: Int): T {
        var max = arr[begin]
        for (i in begin .. end) {
            if(max.toString() <= arr[i].toString()){
                max = arr[i]
            }
        }
        return max
    }
}

fun main(){

    var arr3 : ElementMax<Int> = ElementMax()
    var arr = ArrayList<Int>()
    print("Enter Number of Element: ")
    var n = readLine()!!.toInt()
    println("--------------------")
    for(i in 0 until n){
        print("Element ${i+1} : ")
        var a = readLine()!!.toInt()
        arr.add(a)
    }
    do {
        println("--------------------")
        print("Begin: ")
        var f = readLine()!!.toInt()
        print("End ")
        var s = readLine()!!.toInt()
        if (f >= s) {
            print("Enter Again")
        }
        else {
            println("-------------------")
            println("Element Max in [$f,$s]: ${arr3.elementMax(arr, f-1, s-1) }")
            println()
        }
        var again : String
        print("Press 'n' to go back to input!!")
        again = readln().toString()
    }while(again == "n")

}
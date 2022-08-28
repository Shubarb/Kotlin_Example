package com.example.kotlinassignment.Assignment4

open class Element3<T> {
    fun elementSwap(arr: ArrayList<T>, element1: Int, element2: Int) {
        for (i in 0 until arr.size) {
           var swap = arr[element1]
            arr[element1] = arr[element2]
            arr[element2] = swap
        }
    }
}

fun main(){

    var arr3 : Element3<Int> = Element3()
//    var arr: Array<Int> = arrayOf(1,2,5,4,6)
    var arr = ArrayList<Int>()
    print("Enter Number of Element: ")
    var n = readLine()!!.toInt()
    println("--------------------")
    for(i in 0 until n){
        print("Element ${i+1} : ")
        var a = readLine()!!.toInt()
        arr.add(a)
    }
    println("--------------------")
    print("First Element want swap: ")
    var f = readLine()!!.toInt()
    print("Second Element want swap: ")
    var s = readLine()!!.toInt()
    arr3.elementSwap(arr,f, s)
    println("-------------------")
    print("Swap First Element with Second Element: ")
    for(i in 0 until arr.size) {
       if(i == arr.size-1){
           print(arr[i].toString())
       }else print(arr[i].toString() + ", ")
    }
}

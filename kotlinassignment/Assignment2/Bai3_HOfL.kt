package com.example.kotlinassignment.Assignment2

fun sum(n: Int): Int {
    if (n == 1) return 1;
    return sum(n - 1) + n;
}

fun main(){
    val sl: MutableList<Int> = mutableListOf()
    print("Nhap so: ")
    var n: Int = 0
    n = readLine()!!.toInt()
    var sum: Int = 0
    if(n>0){
        for (i in n downTo 1){
            sl.add(i)
        }
        for(i in 0 until sl.size){
            if (i == sl.size-1)
                print(sl[i].toString())
            else print(sl[i].toString() + "+")
        }
        print( " = ${sum(n)}" )}
    else print("nhap so >0")
}
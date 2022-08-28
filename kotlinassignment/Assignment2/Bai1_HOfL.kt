package com.example.kotlinassignment.Assignment2

fun soNguyenTo(num:Int):Boolean {
    var i: Int = 2
    var flag = true

    while (i <= num / 2) {
        if (num % i == 0) {
            flag = false
            break
        }
        ++i
    }
    return flag
}

fun resultBoolean(): ((Int) -> Boolean){
    return ::soNguyenTo
}

fun main(args: Array<String>) {
    val soNguyenTo = resultBoolean()
    var num: Int = 0
    print("nhập số: ")
    num = readLine()!!.toInt()

    for (num1 in 1 until num){
        for(num2 in 1 until num){
            if(soNguyenTo(num1) == true && soNguyenTo(num2) == true) {
                if(num1 + num2 == num && num1 <= num2){
                    print("$num=$num1+$num2 \n")
                }
            }
        }
    }
}
package com.example.kotlinassignment.Assignment1

fun main(args: Array<String>){
    var months: Int = 0
    var years: Int =0
    print("Enter Year: ")
    var y:String? = readLine()
    print("Enter Month: ")
    var m:String? = readLine()
    if(m != null)
        months=m.toInt()
    if(y != null)
        years =y.toInt()
    when(months) {
        1,3,5,7,8,10,12 -> println("tháng${months},năm${years} có 30 ngày")
        4,6,9,11 -> print("tháng${months},năm${years} có 31 ngày")
        2 -> if ((years%4==0 && years%100!=0) || years%400 == 0  ) {
            print("Tháng 2 năm ${years} có 29 ngày")}
        else print("Tháng 2 năm ${years} có 28 ngày")
    }
}
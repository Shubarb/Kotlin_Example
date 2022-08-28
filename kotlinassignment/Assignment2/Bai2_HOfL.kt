package com.example.kotlinassignment.Assignment2

fun soNguyenTos(num:Int):Boolean {
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

//fun resultSoGiua(): ((Int, Int) -> Int){
//    return ::soGiua
//}

fun main() {
    print("Nhap a: ")
    val a: Int = readLine()!!.toInt()
    print("Nhap b: ")
    val b: Int = readLine()!!.toInt()

//    val soGiua = resultSoGiua()
//    soGiua(a,b)

    val sl: MutableList<Int> = mutableListOf()

    if(a <= 0 && b <= 0){
        print("Nhap lai di")
    }else if (a<b){

        for(i in a+1 until b){
            if(soNguyenTos(i) == true ){
                sl.add(i)
            }
        }
        print("So nguyen to giua $a và $b là: ")
        for(i in 0 until sl.size){
            if (i == sl.size-1)
                print(sl[i].toString())
            else print(sl[i].toString() + ", ")
        }
    }else{
        for(i in b+1 until a){
            if(soNguyenTos(i) == true ){
                sl.add(i)
            }
        }
        print("So nguyen to giua $b và $a là: ")
        for(i in 0 until sl.size){
            if (i == sl.size-1)
                print(sl[i].toString())
            else print(sl[i].toString() + ", ")
        }
    }
}
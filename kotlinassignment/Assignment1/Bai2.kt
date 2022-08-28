package com.example.kotlinassignment.Assignment1

fun main(){
    var i: Int = 0
    //var base: Int
    var binary: String = " "
    var hex: String = " "
    val bin_hexnumber: String = "0123456789ABCDEF"
    while (true) {
        print("Enter Decimal:")
        i = readLine()!!.toInt() // đọc dòng chứa input roi chuyen sang kieu int
        if( i>9 && i<100) {
            break
        }
    }
// Do k sử dụng hàm nên tạo ra 2 biến a và b bằng  i để sử dụng
    var a:Int = i
    var b:Int = i

// Binary
    while (a>0){
        var surplus_Bin = a % 2
        binary += bin_hexnumber.get(surplus_Bin)
        a /= 2

    }
    println("Binary of Decimal: ${binary.reversed()}")

// Hexa
    while (b>0){
        var surplus_Hex = b % 16
        hex += bin_hexnumber.get(surplus_Hex)
        b /= 16
    }
    println("Binary of Hexa: ${hex.reversed()}")

}


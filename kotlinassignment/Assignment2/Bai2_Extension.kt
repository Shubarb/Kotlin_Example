package com.example.kotlinassignment.Assignment2

fun main() {
    print("Enter Hexa: ")
    var hex = readln()
    hex.toBinString()
}
fun String.toBinString() {
    var Binary = this.toCharArray()
    var b: String = " "
    for (i in 0..Binary.size - 1) {
        when (Binary[i]) {
            '0' -> b += "0000 "
            '1' -> b += "0001 "
            '2' -> b += "0010 "
            '3' -> b += "0011 "
            '4' -> b += "0100 "
            '5' -> b += "0101 "
            '6' -> b += "0110 "
            '7' -> b += "0111 "
            '8' -> b += "1000 "
            '9' -> b += "1001 "
            'A' -> b += "1010 "
            'B' -> b += "1011 "
            'C' -> b += "1100 "
            'D' -> b += "1101 "
            'E' -> b += "1110 "
            'F' -> b += "1111 "
        }
    }
    print("Bin: $b")
}

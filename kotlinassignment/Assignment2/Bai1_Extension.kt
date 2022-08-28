package com.example.kotlinassignment.Assignment2
class Convert{
    var Hexa: String = " "
    fun printConvert() {
        print( "Hexadecimal: ${Hexa}")
    }
}
fun main(){
    var dec: Int = 0
    print("Enter Decimal: ")
    dec = readLine()!!.toInt()

    var convert = Convert()
    convert.toHexString(dec, convert)
    convert.printConvert()
}

fun Convert.toHexString(number: Int, conve: Convert){
    conve.Hexa = number.toString(16)
}

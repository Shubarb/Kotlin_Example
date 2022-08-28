package com.example.kotlinassignment.Assignment3

open class Customer() {
    var nGoods:Int = 0
    var price:Int = 0
    var paid:Int = 0
    open fun inputInfo() {
        print("Number of Goods: ")
        nGoods = readLine()!!.toInt()
        print("Price: ")
        price = readLine()!!.toInt()
    }
    open fun showInfo() {
//        print("Number of Goods: ")
//        println(nGoods)
//        print("Price: ")
//        println(price)
    }
}
class TypeA : Customer() {
    override fun showInfo() {
        super.showInfo()
        print("Paid: ")
        paid = Tinhtien(price, nGoods)
        println(paid)
    }
    open fun Tinhtien(p: Int, n: Int) : Int {
        return (p*n + (p*n*10)/100)
    }
}
class TypeB() :Customer() {
    var sale: Int = 0
    var yearCare : Int = 0
    override fun inputInfo() {
        super.inputInfo()
        print("Number of Year Customer care: ")
        yearCare = readLine()!!.toInt()
    }
    override fun showInfo() {
        super.showInfo()
        print("%Sale: ")
        sale = Sale(yearCare)
        println(sale)
        print("Paid: ")
        paid = Paid(price, nGoods, sale)
        println(paid)

    }
    fun Sale(yCare:Int) : Int {
        var sales : Int
        sales = yCare*5
        if (sales <= 50) {
            return sales
        }
        else return (50)
    }
    fun Paid(nGoods: Int, price: Int, sale: Int): Int{
        var paid2: Int
        paid2 = (nGoods*price)*(1-(sale/100)) + (nGoods*price)*(1-(sale/100))*(10/100)
        return paid2
    }
}

class TypeC() : Customer() {
    override fun showInfo() {
        super.showInfo()
        print("Paid: ")
        paid = Paid3(price, nGoods)
        println(paid)
    }
    fun Paid3(prices: Int, nGoods: Int) : Int {
        return (prices*nGoods*(50/100) + (prices*nGoods*10)/100)
    }
}
fun main() {
    val typeA: MutableList<TypeA> = mutableListOf()
    val typeB: MutableList<TypeB> = mutableListOf()
    val typeC: MutableList<TypeC> = mutableListOf()
    do {
        print("Enter Type: ")
        val select = readLine()!!.toString()
        if (select == "A") {
            print("Number Customer: ")
            val n: Int = readln().toInt()
            println()
            for (i in 1..n) {
                println("Customer $i: ")
                val CusA: TypeA = TypeA()
                CusA.inputInfo()
                typeA.add(CusA)
            }
            println("----------------------")
            var sum: Int = 0
            for (i in 1..n) {
                println()
                println("Customer $i: ")
                typeA[i - 1].showInfo()
                sum = sum + typeA[i - 1].paid
            }
            print("Sum paid of Customer A: ")
            println(sum)
        }
        if (select == "B") {
            print("Number Customer: ")
            val n: Int = readln().toInt()
            println()
            for (i in 1..n) {
                println("Customer $i: ")
                val CusB: TypeB = TypeB()
                CusB.inputInfo()
                typeB.add(CusB)
            }
            println("----------------------")
            var sum: Int = 0
            for (i in 1..n) {
                println()
                println("Customer $i: ")
                typeB[i - 1].showInfo()
                sum = sum + typeB[i - 1].paid
            }
            print("Sum paid of Customer B: ")
            println(sum)
        }
        if (select == "C") {
            print("Number Customer: ")
            val n: Int = readln().toInt()
            println()
            for (i in 1..n) {
                println("Customer $i: ")
                val CusC: TypeC = TypeC()
                CusC.inputInfo()
                typeC.add(CusC)
            }
            println("----------------------")
            var sum: Int = 0
            for (i in 1..n) {
                println()
                println("Customer $i: ")
                typeC[i - 1].showInfo()
                println("=>")
                sum = sum + typeC[i - 1].paid
            }
            print("Sum paid of Customer C: ")
            println(sum)
        }
        var out: String
        println("                  -------                  ")
        println("------------------Qua màn------------------")
        println("                  -------                  ")
        print("Close Program: (y/n): ")
        out  = readln().toString()
    }while (out == "n")
}
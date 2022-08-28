package com.example.kotlinassignment.Assignment3

open class Person (){
    var name: String = " "
    var sex: String = " "
    var birthday: String = " "
    var addr: String = " "

    open fun inputInfo() {
        println("Name: ")
        name  = readLine()!!.toString()
        println("sex: ")
        sex  = readLine()!!.toString()
        println("Birthday: ")
        birthday  = readLine()!!.toString()
        println("Address: ")
        addr  = readLine()!!.toString()
    }

    open fun showInfo(){
        println("Name: $name")
        println("Sex: $sex")
        println("Birthday: $birthday")
        println("Address: $addr")

    }
}

//gggggggggggggggggggggggggggggggggg
class  Student: Person(){
    var codeSv : String = " "
    var mScore: Double = 0.0
    var email: String = " "

    override fun inputInfo() {
        super.inputInfo()
        println("Code Student: ")
        codeSv  = readLine()!!.toString()
        println("Medium score: ")
        mScore  = readLine()!!.toDouble()
        println("Email: ")
        email  = readLine()!!.toString()
    }

    override fun showInfo() {
        super.showInfo()
        println("Code Student: $codeSv")
        println("Medium score: $mScore")
        println("Email: $email")
    }

    fun scholarship (){
        if (mScore >= 8.0){
            println("Scholarship")
        }
    }
}

class Teacher: Person(){
    var nClass : String = " "
    var salaryOneHour: Double = 0.0
    var hourPerMonth: Int = 0

    override fun inputInfo() {
        super.inputInfo()
        println("Code Class: ")
        nClass  = readLine()!!.toString()
        if(nClass[0]!= 'G' && nClass[0]!= 'H' && nClass[0]!= 'I' && nClass[0]!= 'K' && nClass[0]!= 'L' && nClass[0]!= 'M' ){
            println("Class code must start with G,H,J,K,L,M")
            print("Enter again: ")
            nClass  = readLine()!!.toString()
        }
        println("Salary/hour: ")
        salaryOneHour  = readLine()!!.toDouble()
        println("Hour/month: ")
        hourPerMonth  = readLine()!!.toInt()
    }

    override fun showInfo() {
        super.showInfo()
        println("Code Class: $nClass")
        println("Salary/hour: $salaryOneHour")
        println("Hour/month: $hourPerMonth")
    }

    fun SalaryReal (){
        if (nClass[0] == 'G' || nClass[0] == 'H' || nClass[0] == 'I' || nClass[0] == 'K'){
            var salaryReal = salaryOneHour * hourPerMonth
        }
        else if(nClass[0] == 'L' || nClass[0] == 'M' ){
            var salaryReal = salaryOneHour * hourPerMonth + 500000
        }
    }
}

fun main(){
    val t1 = Student()
    t1.scholarship()
    println()
}
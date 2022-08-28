package com.example.kotlinassignment.Assignment3

open class Employee{
    var name: String = " "
    var birthday: String = " "

//    constructor(Name:String, Birthday:String) {
//        name = Name
//        birthday = Birthday
//    }

    open fun inputInfo() {
        print("Name: ")
        name  = readLine()!!.toString()
        print("Birthday: ")
        birthday  = readLine()!!.toString()
    }

    open fun showInfo(){
        println("Name: $name")
        println("Birthday: $birthday")
    }
}


class  officerDefnition(): Employee(){
    var worksDay: Int = 0
    override fun inputInfo() {
        super.inputInfo()
        print("Works Day: ")
        worksDay  = readLine()!!.toInt()
    }

    override fun showInfo() {
        super.showInfo()
        println("Works Day: $worksDay")
        officerSalary(worksDay)
    }
    fun officerSalary(w:Int):Int {
        var salary = w*100000
        println("Salary Officer Defnition: $salary")
        return salary
    }
}

class workerDefnition(): Employee(){
    var baseSalary: Double = 0.0
    var numProducts: Double = 0.0

    override fun inputInfo() {
        super.inputInfo()
        print("Base Salary: ")
        baseSalary  = readLine()!!.toDouble()
        print("Number of Products: ")
        numProducts  = readLine()!!.toDouble()
    }

    override fun showInfo() {
        super.showInfo()
        println("Works Day: $baseSalary")
        println("Number of Products: $numProducts ")
        workerSalary()
    }

    fun workerSalary(): Double{
        println("Base Salary: $baseSalary")
        var salary = baseSalary + numProducts*5000
        println("Salary Worker Defnition: $salary")
        return salary
    }
}


fun main(args: Array<String>){
    var arroff: MutableList<officerDefnition> = mutableListOf()
    var arrwork: MutableList<workerDefnition> = mutableListOf()
    fun selectOfficer(){

        println("--------Select Perfect--------")
        println("Select Input/Show Officer!!")
        println("1.Input Employee")
        println("2.Show Manager Employ")
        print("Select: ")
        val select:Int = readLine()!!.toInt()
        println()
        when(select) {
            1 -> {
                println("Input Officer:")
                print("Number of Employee: ")
                val employ = readLine()!!.toInt()

                for(i in 0 until employ){
                    var nv: officerDefnition = officerDefnition()
                    nv.inputInfo()
                    arroff.add(nv)
                    println()
                }
            }
            2 -> {
                print("Information Exployee want show:")
                val stt = readln().toInt()
                arroff[stt-1].showInfo()
            }
        }
    }
    fun selectWorker() {

        println("--------Select Perfect--------")
        println("Select Input/Show Worker!!")
        println("1.Input Employee")
        println("2.Show Manager Employ")
        print("Select: ")
        var select:Int = readln().toInt()
        println()
        when(select) {
            1 -> {
                println("Input Worker:")
                print("Number of Employee: ")
                var employ = readLine()!!.toInt()

                for(i in 0 until employ){
                    var nv: workerDefnition = workerDefnition()
                    nv.inputInfo()
                    arrwork.add(nv)
                    println()
                }
            }
            2 -> {
                print("Information Exployee want show:")
                val stt = readln().toInt()
                arrwork[stt-1].showInfo()
            }
        }
    }
    do{
        println("--------Select Perfect--------")
        println("Select Type Employee")
        println("1.Select Officer")
        println("2.Select Worker")
        print("Select: ")
        var select:Int = readln().toInt()
        when(select){
            1 -> {
                selectOfficer()
            }
            2 -> {
                selectWorker()
            }
        }
        var out: String
        println("------------------Qua màn------------------")
        print("Close Program: (y/n): ")
        out  = readln().toString()
    }while (out == "n")
}
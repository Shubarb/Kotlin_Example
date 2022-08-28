package com.example.kotlinassignment.Assignment5

open class Employee {
    var name: String = " "
    var sex: String = " "
    var birthday: String = " "
    var numberPhone: String = " "
    var level: String = " "

    open fun InputInfo() {
        print("Name: ")
        name = readLine()!!.toString()
        enterObligatory(name)
        print("Sex: ")
        sex = readLine()!!.toString()
        enterObligatory(sex)
        Sex(sex)
        print("Birthday: ")
        birthday = readLine()!!.toString()
        enterObligatory(birthday)
        Birthday(birthday)
        print("Number phone: ")
        numberPhone = readLine()!!.toString()
        enterObligatory(numberPhone)
        print("Level: ")
        level = readLine()!!.toString()
        Level(level)

    }

    open fun ShowInfo() {
        println("Name: $name")
        println("Sex: $sex")
        println("Birthday: $birthday")
        println("Number phone: $numberPhone")
        println("Level: $level")
        println()
    }


    open fun enterObligatory(n: String): String {
        if (n == "") {
            println("Enter Obligatory")
            var d = readLine().toString()
            return enterObligatory(d)
        }
        return n
    }

    open fun Sex(n: String): String {
        if (n != "nam" && n != "nữ") {
            print("Enter nam or nữ: ")
            val d = readLine().toString()
            return Sex(d)
        }
        else return n
    }

    open fun Birthday(n: String): String {
        var bir = n.toCharArray()
        if (bir[2] != '/' && bir[5] != '/') {
            print("Enter Again birthday dd/mm/yyyy: ")
            var d = readLine().toString()
            return Birthday(d)
        }
        else return n
    }

    open fun Level(n: String): String {
        if (n != "Trung cấp" && n != "Cao đẳng" && n != "Đại học") {
            print("Enter Again Level 'Trung cấp', 'Cao đẳng', 'Đại học': ")
            var d = readLine().toString()
            return Level(d)
        }
        else return n
    }
}
fun main() {
    var arr: MutableList<Employee> = mutableListOf()
    do {
        println("Please select : 'add', 'display', 'update', 'find' !!")
        print("Select: ")
        var select: String = readLine().toString()
        println()
        fun Add() {
            val addnew: Employee = Employee()
            addnew.InputInfo()
            arr.add(addnew)
        }

        fun Display() {
            if (arr.size >= 1) {
                for (i in 0 until arr.size) {
                    arr[i].ShowInfo()
                }
            } else {
                println("Array No imformation!! Select add, please!")
            }
        }

        fun Update() {
            println("Find id or name:")
            val find: String = readLine().toString()
            println()
            when (find) {
                "id" -> {
                    print("Enter Id: ")
                    val id = readLine().toString()
                    if (id == "") {
                        println("Please enter your information!!")
                        val a = readLine()!!.toInt()
                        if (a >= 1) {
                            arr[a - 1].InputInfo()
                        } else {
                            println("Not found information")
                        }
                    } else {
                        if (id.toInt() >= 1) {
                            arr[id.toInt() - 1].InputInfo()
                        }
                    }
                }
                "name" -> {
                    print("Enter Name: ")
                    val id = readLine().toString()
                    var count = 0
                    if (id == "") {
                        println("Please enter your information!!")
                        val a = readLine().toString()
                        for (i in 0 until arr.size) {
                            if (a == arr[i].name) {
                                arr[i].InputInfo()
                                count++
                            }
                        }
                        if (count == 0) {
                            println("Not found information")
                        }
                    } else {
                        for (i in 0 until arr.size) {
                            if (id == arr[i].name) {
                                arr[i].InputInfo()
                                count++
                            }
                        }
                        if (count == 0) {
                            println("Not found information")
                        }
                    }
                }
            }
        }

        fun Find() {
            println("Find id or name:")
            val find: String = readLine().toString()
            println()
            when (find) {
                "id" -> {
                    print("Enter Id: ")
                    val id = readLine().toString()
                    if (id == "") {
                        println("Please enter your information!!")
                        val a = readLine()!!.toInt()
                        if (a >= 1) {
                            arr[a - 1].ShowInfo()
                        } else {
                            println("Not found information")
                        }
                    } else {
                        if (id.toInt() >= 1) {
                            arr[id.toInt() - 1].ShowInfo()
                        }
                    }
                }
                "name" -> {
                    print("Enter Name: ")
                    val id = readLine().toString()
                    var count = 0
                    if (id == "") {
                        println("Please enter your information!!")
                        val a = readLine().toString()
                        for (i in 0 until arr.size) {
                            if (a == arr[i].name) {
                                arr[i].ShowInfo()
                                count++
                            }
                        }
                        if (count == 0) {
                            println("Not found information")
                        }
                    } else {
                        for (i in 0 until arr.size) {
                            if (id == arr[i].name) {
                                arr[i].ShowInfo()
                                count++
                            }
                        }
                        if (count == 0) {
                            println("Not found information")
                        }
                    }
                }
            }
        }
        when (select) {
            "add" -> Add()
            "display" -> Display()
            "update" -> Update()
            "find" -> Find()
        }
        var again: String
        println("---------------------")
        print("Press 'n' to go back to input!!  ")
        again = readln().toString()
    } while (again == "n")
}


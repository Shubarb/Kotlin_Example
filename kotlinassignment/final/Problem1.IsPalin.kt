package com.example.kotlinassignment.final


fun palindrome(input: String):Boolean{
    var flag = true
    var i = 0
    var j = input.trim().length -1
    while (j>i){
        if(input[i] != input[j]){
            flag = false
        }
        ++i
        --j
    }
    println(flag)
    return flag
}
fun main(){
    palindrome("abba")
    palindrome("abcdefg")
    palindrome("abba ")
    palindrome("abb a")
}

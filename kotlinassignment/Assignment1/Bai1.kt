package com.example.kotlinassignment.Assignment1

fun main(){
    for(i in 10..200){
        if(i%7 ==0 && i%5 != 0){
            print("${i},")
        }
    }
}
package com.example.kotlinassignment.Assignment1

fun main() {
    print("Enter String: ")
    val EnterString:String = readln()
    val arr: CharArray =EnterString.toCharArray()
    var Count:Int = 1
// đếm số từ
    for (i in arr.indices){
        if (arr[i] == ' '){
                Count ++
        }
    }
    println("Number of words in string: ${Count}")
// xóa space đầu và cuối câu nhưng đang lỗi chưa update
    var f :Int =0
    var l: Int = EnterString.length -1
    while (f<l && arr[f] == ' ')
        f++
    while (f>l && arr[l] == ' ')
        l--
// Viết hoa đầu câu và sau chấm

    for (i in 1 until EnterString.length){
        // nếu ký tự ở đầu dòng thuộc a->z thì viết hoa còn
        if(arr[0] >= 'a' && arr[0] <= 'z' ) {
            arr[0] = arr[0] - 32
        }
        // nếu ký tự đó k ở đầu dòng mà ở sau dâu "." và " " mà chữ đó k viết hoa thì viết hoa nó
        if(i!=0 && arr[i] =='.' && arr[i+1] == ' ' && arr[i+2] >= 'a' && arr[i+2] <= 'z' ){
            arr[i+2] = arr[i+2] - 32
        }
    }
    print("Sentence Caplock: ")
    print(arr)
}
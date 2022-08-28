package com.example.kotlinassignment.Coroutine_demo



import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


///
/* Shubarb*/
///

//Channels cung cấp một cách để chuyển một luồng giá trị giữa các coroutines.
//Channels khá giống với BlockingQueue, tức là nó cũng hoạt động như một Queue là FIFO.
// Nó sử dụng 2 hàm send/receive (tương tự put/take) và 2 hàm này là suspend function tức là nó có khả năng suspend/resume và non-blocking

fun main() = runBlocking {
    val channel = Channel<Int>()          //tạo một channel để transfer một luồng giá trị kiểu Int
    launch{
        for(x in 1..5)
            channel.send((x*x))        //thêm một giá trị vào channel
    }
    repeat(5){
        println(channel.receive())    //trả về giá trị được send sớm nhất, đồng thời remove giá trị đó ra khỏi channel
    }
    println("Done!")
}

//














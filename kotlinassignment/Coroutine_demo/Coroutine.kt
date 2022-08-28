package com.example.kotlinassignment.Coroutine_demo



//1.So sánh giữa suspend và blocking
//Blocking : ví dụ function B chỉ được gọi đến sau khi hàm Main Function được xử lí xong và tất cả chúng được thực thi trên main thread.
//Suspend: Function B khi khởi chạy sẽ tạm dừng (suspend) trên main thread nhưng chúng ta có thể sử dụng tiếp tục nó trên bất kì luồng nào khác.
//->  nếu khai báo hàm bắt buộc phải có từ khóa suspend.
//Runblocking : bắt đầu 1 coroutine mới  và chặn luồng hiện tại cho đến khi hoàn tất

//suspend fun sayHello(){
//    delay(1000L)
//    println("Hello")
//}
//
//fun main(){
//    runBlocking{
//        val hello  = sayHello()
//    }
//    println("World")
//}

//fun main() = runBlocking{
//    repeat(100000){
//      launch{
//            delay(5000L)
//            print(".")
//        }
//    }
//}
//=> sau 5s in ra 100000 dấu "."

//fun main() {
//    repeat(100000){
//            Thread.sleep(5000L)
//            print(".")
//        }
//}
//=> cứ 5s in ra 1 dấu châm, chạy cho đến 100000 dấu "."

//***********************************************************************

//2.CoroutineScope : giúp ta định nghĩa vòng đời của một kotlin coroutine.
//Nó là 1 suspend function nên chỉ tạo ra bên trong suspendfunction khác hoặc trong 1 coroutine

//A.Coroutine Context:
//Là nơi ta chỉ định threadpool xử lí và quản lí vòng đời hay xử lí exception.
//Nó gồm Dispatchers + job + exception

// runBlocking(Dispatchers.IO + Job()){
//}
//GlobalScope.launch(newSingleThreadContext("newSingleThreadContext")
//                    + CoroutineName("CoroutineName")
//                    + NonCancellable){
//}

//* withContext: là 1 suspend function cho phép coroutine chạy code trong block với một context cụ thể ( chuyển sang 1 background thread), nó sẽ chạy code tuần tự

//fun main(){
//    newSingleThreadContext("thread1").use { ctx1 ->
//        println("ctx1 - ${Thread.currentThread().name}")
//        newSingleThreadContext("thread2").use { ctx2 ->
//            println("ctx2 - ${Thread.currentThread().name}")
//            runBlocking(ctx1) {
//                println("Strated  in  ctx1 - ${Thread.currentThread().name}")
//                withContext(ctx2) {
//                    println("Working in ctx2 -${Thread.currentThread().name} ")
//                }
//                println("Back to ctx1 - ${Thread.currentThread().name}")
//            }
//        }
//        println("out of ctx2 block - ${Thread.currentThread().name}")
//    }
//    println("out of ctx1 block -${Thread.currentThread().name}")
//}

//* Dispatchers : xác định thread nào mà coroutine chạy trên đó
//Dispatchers.Main: chạy trên main thread, xử lí những nhiệm vụ liên quan đến view.
//Dispatchers.Unconfined : chạy một coroutine không giới hạn bất kì thread cụ thể nào.
//Dispatchers.IO: chạy trên background thread, xử lí những nhiệm vụ như đọc ghi file, kết nối internet.
//Dispatchers.Default : chạy trên background thread, xử lí những công việc tiêu tốn CPU như sắp xếp 	List,parse json,..
//AndroidScheduler.mainThread, Schedulers.IO hay Schedulers.computation.

//fun main() = runBlocking<Unit> {
//    launch(Dispatchers.Unconfined) {
//        println("Unconfined: đang làm việc ở Thread ${Thread.currentThread().name}")
//    }
//    launch(Dispatchers.Default) {
//        println("Default: đang làm việc ở thread ${Thread.currentThread().name} ")
//    }
//    launch(newSingleThreadContext("MyOwnThread")) {
//        println("NewSingleThreadContext: đang làm việc ở thread ${Thread.currentThread().name}")
//    }
//}

// * Dispatchers.Unconfined khác Dispatchers.Main

//fun main() = runBlocking<Unit> {
//    launch(Dispatchers.Unconfined) {
//        println("Unconfined: đang làm việc ở Thread ${Thread.currentThread().name}")
//        delay(5000L)
//        println("Unconfined: sau delay ở thread ${Thread.currentThread().name}")
//    }
//}

//=> Ban đầu coroutine chưa hạn chế thì nó sẽ chạy trên currentthread mà  crth đang chạy trên mainthr nên nó chạy trên main
// cho đến khi gặp delay(suspendfunction) , sau khi coroutine bắt đầu trở lại thì nó k chạy trên crth nữa mà trên background thread

//* Job: Quản lí lifecycle của coroutine
//“Chẳng hạn khi đang ở 1 màn hình có network và đang tải dữ liệu nhưng lâu quá nên chuyển màn.
// -> function get network k bị hủy khi view bị hủy, nó sẽ chạy trên background thread trả về kết quả để cập nhật lên view .
// nhưng view đã bị destroy khi chuyển màn và app sẽ bị crash
// -> cần xử lí hủy function get network sau khi view bị destroy”

//lệnh join()
//Khi một Coroutine gọi hàm join() này thì Main Thread phải đợi Coroutine này chạy xong task của mình rồi mới chạy tiếp

//fun main() = runBlocking{
//    val job = GlobalScope.launch {
//        delay(5000L)
//        print("World")
//    }
//    print("Hello ")
//    job.join()
//    print(" kotlin")
//}

//* Cancel() : khi gọi cancel() tiến trình đang chạy sẽ bị hủy dựa vào 1 property isActive ( check coroutine còn sống k) trong Job .
//Khi job.isActive == false tiến trình sẽ hủy .
//Một số hàm như delay, tất cả các hàm suspend đều đã tích hợp sẵn isActive vào trong.

//suspend fun test(){
//    Thread.sleep(500L)
//}
//
//fun main() = runBlocking{
//    val job = launch {
//        repeat(1000){
//            i ->
//            println("I'm sleeping $i ...")
////            delay(500L)
//            test()
//        }
//    }
//    delay(1600L)
//    print("Main: I'm tired of waiting! ")
//    job.cancel()
//    print("Main: Now I can quit")
//}

// Còn khi k gặp thì sẽ:

//fun main() = runBlocking {
//    val startTime = System.currentTimeMillis()
//    val job = launch(Dispatchers.Default){
//        var nextPrintTime = startTime
//        var i=0
//        while (i<5){
//            if(System.currentTimeMillis() >= nextPrintTime){
//                println("job: ${i++}")
//                nextPrintTime += 500L
//            }
//        }
//    }
//    delay(1300L)
//    println("main: T mệt lắm rồi")
//    job.cancel()
//    println("main: T đi đây")
//}

// fix dùng isActive

//fun main() = runBlocking {
//    val startTime = System.currentTimeMillis()
//    val job = launch(Dispatchers.Default){
//        var nextPrintTime = startTime
//        var i=0
//        while (isActive){
//            if(System.currentTimeMillis() >= nextPrintTime){
//                println("job: ${i++}")
//                nextPrintTime += 500L
//            }
//        }
//    }
//    delay(1300L)
//    println("main: T mệt lắm rồi")
//    job.cancel()
//    println("main: T đi đây")
//}

// thử dùng finally
//*finally: khi 1 tiến trình bị hủy thì ngay lập tức nó sẽ tìm đến khối finally để chạy ( có thể tranh thủ close hết các nguồn trước khi coroutine bị khai tử)

//fun main() = runBlocking {
//    val job = launch{
//        try{
//            repeat(1000){i->
//                println("$i")
//                delay(500L)
//            }
//        }finally {
//            println("Đây là finally này")
//        }
//    }
//    delay(1300L)
//    println("main: T mệt lắm rồi")
//    job.cancel()
//    println("main: T đi đây")
//}

//Tuy nhiên trong khối finally vẫn có thể bị dừng do có các hàm delay, tất cả suspend đã nói trên

//fun main() = runBlocking {
//    val job = launch{
//        try{
//            repeat(1000){i->
//                println("$i")
//                delay(500L)
//            }
//        }finally {
//            println("Đây là finally này")
//            delay(1000L)
//            println("In t đi mà")
//        }
//    }
//    delay(1300L)
//    println("main: T mệt lắm rồi")
//    job.cancel()
//    println("main: T đi đây")
//}

//Để bất tử trở lại thì ném đống đó vào trong withContext(NonCancellable)

//fun main() = runBlocking {
//    val job = launch{
//        try{
//            repeat(1000){i->
//                println("$i")
//                delay(500L)
//            }
//        }finally {
//            withContext(NonCancellable) {
//                println("Đây là finally này")
//                delay(1000L)
//                println("In t đi mà")
//            }
//        }
//    }
//    delay(1300L)
//    println("main: T mệt lắm rồi")
//    job.cancel()
//    println("main: T đi đây")
//}

//***************************************************************

//Time out
//*withTimeout: giới hạn time die và die sẽ thông báo lỗi đỏ.

//fun main() = runBlocking {
//    withTimeout(1300L){
//        repeat(1000){i ->
//            println("$i")
//            delay(500L)
//        }
//    }
//}

//*withTimeoutOrNull() : tương tự nhưng die thì trả về biến null

//fun main() = runBlocking {
//    val result = withTimeoutOrNull(1300L) {
//        repeat(1000){i ->
//            println("$i")
//            delay(500L)
//        }
//        "Done"
//    }
//    println("Result is $result")
//}

//* Khi một coroutine mới được tạo trong coroutine scope của một coroutine
// thì nó sẽ sử dụng scope và context của coroutine này
// trừ khi nó được khai báo với scope và context riêng.
// Bên cạnh đó thì coroutine này sẽ chỉ hoàn thanh nhiệm vụ khi các coroutine bên trong nó hoàn thành.
// Và khi nó bị hủy thì coroutine bên trong cũng bị hủy theo.

//***************************************************************

//GlobalScope
//*GlobalScope : no sẽ không tự hủy  , và k thể gọi cancel như bình thường

//fun main() = runBlocking<Unit> {
//    val request = launch {
//        GlobalScope.launch {
//            println("Job1: GlobalScope and execute independently!")
//            delay(1000)
//            println("Job1: I am not affected by cancellation")
//        }
//        launch {
//            delay(100)
//            println("Job2: I am a child of the request coroutine")
//            delay(1000)
//            println("Job2: I will not execute this line if my parent request is cancelled")
//        }
//    }
//    delay(500)
//    request.cancel()
//    delay(1000)
//    println("Main: Who has survived request cancellation?")
//}

//***************************************************************

//CoroutineBuilder
//Ý tưởng ban đầu:
// khi muốn tính toán một bài toán nào đó chẳng hạn cộng hai số, nếu dùng 1 coroutine thì nó sẽ phải đợi xuất 2 số ra rồi mới thực hiện phép cộng.
// tuy nhiên ta có thể tạo ra 2 coroutine để thực hiện song song thì time sẽ giảm đi, ta có async

//*launch:  không trả về giá trị, return về kiểu job

//*async:  trả về 1 deferred instance của 1 object như String, Int, …
//
//Và để get giá trị này thì cần hàm await()
//Async{}.await() = withContext nhưng withContext chạy code tuần tự còn async chạy code song song.

//fun main() = runBlocking{
//    var str: Deferred<String> = async { return@async "Sun" }
//    var int: Deferred<Int> = async { printInt() }
//    var unit: Deferred<Unit> = async {  }
//    println("Int = ${int.await()}")
//    println("String = ${str.await()}")
//}
//
//fun printInt(): Int{
//    return 10
//}





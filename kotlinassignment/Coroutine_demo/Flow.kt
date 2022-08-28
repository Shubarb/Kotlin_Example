package com.example.kotlinassignment.Coroutine_demo



//Khối flow { } là một builder function giúp ta tạo ra 1 đối tượng Flow.
//Code bên trong flow { } có thể suspend, điều này có nghĩa là chúng ta có thể gọi các suspend function trong khối flow { }. Vì vậy function foo() gọi khối flow { } không cần thiết phải là suspend function nữa.
//Hàm emit dùng để emit các giá trị từ Flow. Hàm này là suspend function
//Hàm collect dùng để get giá trị được emit từ hàm emit. Hàm này cũng là suspend function,và code bên trong flow k chạy cho đến khi gọi hàm này
//
//fun foo(): Flow<Int> = flow{
//    for(i in 1..3){
//        delay(1000)
//        emit(i)
//    }
//}
//fun main() = runBlocking {
//    launch {
//        println(Thread.currentThread().name)
//        for(k in 1..3){
//            delay(900)
//            println("I'm not blocked $k")
//        }
//    }
//    val time = measureTimeMillis {
//        foo().collect { value -> println(value)  }
//    }
//    println("$time s")
//}

// => Flow xử lý bất đồng bộ. Nó sử dụng suspend function collect để không block main thread trong khi chờ đợi item tiếp theo được emit.
//***********************************************************

//Flow cũng bị ảnh hưởng bởi suspend ( chỉ bị cancel khi bị suspend)
// ví dụ cancel bởi timeout

//// bị suspend
//fun foo(): Flow<Int> = flow {
//    for (i in 1..3){
//        delay(2000)
//        println("Emitting $i")
//        emit(i)
//    }
//}
//// không bị suspend
//fun foo2(): Flow<Int> = flow {
//    for (i in 1..3){
//        Thread.sleep(2000)
//        println("Emitting $i")
//        emit(i)
//    }
//}
//fun main() = runBlocking{
//    withTimeoutOrNull(5000){
//        foo2().collect { value -> println(value)  }
//    }
//    println("Done")
//}

//************************************************************************

// Cách tạo flow
//* flowof()

//fun main() = runBlocking {
//    val data = flowOf(1,"abc",3,4,"def")
//    data.collect { println(it) }
//}

//* asFlow() : convert collections, arrays, sequences hay T ,.. sang Flow

//fun main() = runBlocking {
//    listOf(1,"abc",3,4,"def").asFlow().collect { println(it) }
//}

//***********************************************************************

// Toán tử take()
//nếu bạn muốn nguồn thu lấy một lượng giới hạn các phần tử được phát ra từ nguồn phát.

//fun numbers(): Flow<Int> = flow{
//    try {
//        emit(1)
//        emit(2)
//        println("this line will not execute")
//        emit(3)
//    }catch(e: CancellationException){
//        println("exception")
//    }finally {
//        println("close")
//    }
//}
//
//fun main() = runBlocking {
//    numbers().take(2).collect {
//        value -> println(value)
//    }
//}

//***********************************************************************

// Toán tử transform()
// dùng để biến đổi giá trị được phát ra từ nguồn phát trước khi emit cho nguồn thu nhận nó. Ngoài ra, nó còn có các công dụng khác như nguồn thu có thể bỏ qua (skip) các giá trị mà nó không muốn nhận từ nguồn phát hoặc chúng ta có thể emit một giá trị nhiều hơn một lần (có nghĩa là phát 10 giá trị nhưng nhận có thể tới 20 giá trị)

//fun main() = runBlocking {
//    (1..9).asFlow().transform { value ->
//    if(value % 2 == 0 ){
//        emit(value *value)
//        emit(value*value*value)
//    }
//    }.collect { response -> println(response) }
//}

//***********************************************************************

// Toán tử map()
// biến đổi phần tử nhận được như toán tử transform nhưng khác ở chỗ: toán tử transform cho phép ta skip phần tử hoặc emit một phần tử nhiều lần còn toán tử map thì không thể skip hoặc emit một phần tử nhiều lần. Với mỗi phần tử nhận được từ nguồn phát, nguồn thu sẽ xử lý biến đổi và emit một và chỉ một giá trị cho nguồn thu (tức là phát 1 thì thu 1, phát 10 thì thu 10).

//fun main() = runBlocking {
//    (1..9).asFlow().map{ it*it}
//    .collect { response -> println(response) }
//}

//***********************************************************************

// Toán tử filter()
// lọc ra các giá trị thỏa mãn điều kiện và bỏ qua các giá trị không thỏa mãn điều kiện từ nguồn phát.

//fun main() = runBlocking {
//    (1..5).asFlow().filter {
//        println("filter $it")
//        it % 2 == 0
//    }.collect {
//        println("collect $it")
//    }
//}

//***********************************************************************

// Toán tử onEach()
// Toán tử này dùng khi ta muốn thực hiện một action gì đó trước khi value từ flow được emit.

//fun main() = runBlocking {
//    val nums = (1..3).asFlow().onEach { delay(3000) }
//    val startTime = System.currentTimeMillis()
//    nums.collect { value -> println("$value at ${System.currentTimeMillis() - startTime}ms from start") }
//}

//***********************************************************************

// Toán tử reduce()
// cực hữu ích khi ta cần tính tổng cộng dồn tất cả giá trị được phát ra từ nguồn phát

//fun main() = runBlocking {
//    val sum = (1..3).asFlow().map{it*it}.reduce{a, b -> a+b}
//    println(sum)
//}

// Hàm reduce không trả về Flow nên chúng ta không cần collect. Nó chỉ trả về đúng 1 giá trị sau khi cộng dồn tất cả giá trị từ nguồn phát

//fun main() = runBlocking {
//    val sum = listOf("a","b","c","d").asFlow().reduce{a, b ->
//        println("tổng tích luwxy $a")
//        println("giá trị mới $b")
//        a+b
//    }
//    println("ket quả $sum")
//}

//***********************************************************************

// Toán tử fold()
// Nó cũng có chức năng chính là tính tổng, tuy nhiên nó khác ở chỗ hàm reduce tính tổng từ con số 0 còn hàm fold tính tổng từ một giá trị cho trước

//fun main() = runBlocking {
//    val sum = (1..3).asFlow().fold(initial = 10){
//        a,b ->
//        println(" tổng giá trị tích lũy $a")
//        println("giá trị mới $b")
//        a+b
//    }
//    println("ket qua $sum")
//}

//***********************************************************************

// Toán tử toList(), toSet()
// Toán tử này giúp chúng ta convert một Flow thành một ArrayList hoặc LinkedHashSet

//fun main() = runBlocking {
//    val list: List<String> = listOf("a","b", "c","d").asFlow().toList()
//    val set: Set<Int> = (1..5).asFlow().toSet()
//    println("${list.javaClass} $list")
//    println("${set.javaClass} $set")
//}

//***********************************************************************

// Toán tử first()
// get ra phần tử đầu tiên trong flow

//fun main() = runBlocking {
//    val a: List<Int> = listOf(1,2,3,4,5,6,7,9).asFlow().first()
//    println(a)
//}

// hàm first{} thêm điều kiện nữa

//fun main() = runBlocking {
//    val a: List<Int> = listOf(1,2,3,4,5,6,7,9).asFlow().first{it%2==0}
//    println(a)
//}

//***********************************************************************

// Toán tử single(), singleOrNull()
//  check chắc chắn rằng flow chỉ có một phần tử và nó sẽ return giá trị đó.

//fun main() = runBlocking {
//    var a: Int =
////    listOf(10).asFlow().single()
////    listOf(1,2).asFlow().single()
//    listOf<Int>().asFlow().single()
//    println(a)
//}

// singleOrNull() sẽ trả về null nếu flow không có phần tử nào

//fun main() = runBlocking {
//    val a:Int? = listOf(10).asFlow().singleOrNull()
//    val b:Int? = listOf<Int>().asFlow().singleOrNull()
////    listOf(1,2).asFlow().singleOrNull()
//    println(a.toString())
//    println(b.toString())
//}

//***********************************************************************

// Toán tử zip()
// dùng để zip 2 flow lại (giống như hàm zip trong Sequence hay List). Có nghĩa là nó sẽ lấy 1 phần tử bên flowA và 1 phần tử bên flowB để kết hợp lại tạo ra một phần tử mới.

//fun main() = runBlocking {
//    var nums = (1..3).asFlow()
//    var strs = flowOf("one","two","three")
//    nums.zip(strs){
//        a,b -> "$a -> $b"
//    }.collect { println(it) }
//}

//***********************************************************************

// Toán tử combine()
// tương tự như zip nhưng ở sau lần kết hợp 1 thì zip đợi strs emit phần tử 2 thì mới kết hợp còn combine thì kết hợp luôn nums 2 với strs1

//fun main() = runBlocking {
//    val nums = (1..3).asFlow().onEach { delay(300) }
//    val strs = flowOf("one","two","three").onEach { delay(400) }
//    val startTime = System.currentTimeMillis()
//    nums.combineLatest(strs) { a,b -> "$a -> $b"}.collect{ value ->
//        println("$value at ${System.currentTimeMillis() - startTime}ms from start ")  }
//}

//***********************************************************************

// Toán tử flatMapConcat()
//  toán tử này sẽ chờ đợi đến khi flow kia hoàn thành cả 2 emit rồi mới bắt đầu collect data tiếp theo từ flow này

//fun requestFlow(i: Int): Flow<String> = flow{
//    emit("$i: First")
//    delay(500)
//    emit("$i: Second")
//}
//
//fun main() = runBlocking<Unit> {
//    val startTime = System.currentTimeMillis()
//    (1..3).asFlow().onEach { delay(100)}
//        .flatMapConcat { requestFlow(it) }
//        .collect { value ->
//            println("$value at ${System.currentTimeMillis() - startTime}ms from start ")
//        }
//}

//***********************************************************************

// Toán tử flatMapMerge()
// toán tử này collect tất cả các luồng đến và hợp nhất các giá trị của chúng thành một luồng duy nhất để các giá trị được phát ra càng sớm càng tốt

//fun requestFlow(i:Int): Flow<String> = flow{
//    emit("$i: First")
//    delay(500)
//    emit("$i: Second")
//}
//
//fun main() = runBlocking<Unit> {
//    val startTime = System.currentTimeMillis()
//    (1..3).asFlow().onEach { delay(100) }
//        .flatMapMerge { requestFlow(it) }
//        .collect { value ->
//            println("$value at ${System.currentTimeMillis() - startTime}ms from start ")
//        }
//}

// Toán từ này nó không đợi flowB emit xong phần tử Second như flatMapConcat mà nó tiếp tục collect tiếp từ flowA.

//***********************************************************************

// Toán tử flatMapLatest()
// flatMapLatest đã hủy tất cả code trong khối của nó flowB khi nó gặp delay trong flowB và tiếp tục collect data từ flowA

//fun requestFlow(i:Int): Flow<String> = flow{
//    emit("$i: First")
//    delay(500)
//    emit("$i: Second")
//}
//
//fun main() = runBlocking<Unit> {
//    val startTime = System.currentTimeMillis()
//    (1..3).asFlow().onEach { delay(100) }
//        .flatMapLatest { requestFlow(it) }           // đang lỗi
//        .collect { value ->
//            println("$value at ${System.currentTimeMillis() - startTime}ms from start ")
//        }
//}

// nguyên nhân bị hủy là do hàm delay ở flow B

//***********************************************************************

// Flow Context
// code trong khối flow{} chạy trên context nguồn thu, tức là nó đã chạy với context nào thì mãi chạy vs context đó.
// không thể ép dùng withContext như coroutine được, nó sẽ throw Exception

//fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")
//
//fun foo(): Flow<Int> = flow {
//    log("Started foo flow")
//    for(i in 1..3){
//        emit(i)
//    }
//}
//
//fun main() = runBlocking {
//    foo().collect {
//        value -> log("Collect $value")
//    }
//}

//***********************************************************************

//Toán tử flowOn
// flowOn sẽ cho phép code trong khối flow được chạy trên bất kỳ context nào ta muốn

//fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")
//
//fun foo(): Flow<Int> = flow {
//    log("Started foo flow")
//    for(i in 1..3){
//        Thread.sleep(100)
//        log("Emitting $i")
//        emit(i)
//    }
//}.flowOn(Dispatchers.Default)
//
//fun main() = runBlocking {
//    foo().collect {
//        value -> log("Collect $value")
//    }
//}

// toán tử flowOn không có khả năng change context của coroutine đang chạy. Nó đã tạo ra 1 coroutine khác chạy trên context do chúng ta set trong hàm flowOn().

//***********************************************************************

// Flow Exception
//Nguồn thu có khả năng xảy ra throw Exception nếu code chạy trong nguồn phát xảy ra Exception

//fun foo(): Flow<Int> = flow{
//    for(i in 3 downTo -3){
//        println("3/$i = ${3/i}")
//        emit(i)
//    }
//}
//fun main() = runBlocking {
//    foo().collect {
//        value ->
//        println("Value = $value")
//    }
//}

// Exception xảy ra do i = 0
// try/catch để catch exception nhưng vẫn lỗi do i = 0

//fun foo(): Flow<Int> = flow{
//    for(i in 3 downTo -3){
//        println("3/$i = ${3/i}")
//        emit(i)
//    }
//}
//fun main() = runBlocking {
//    try {
//        foo().collect { value ->
//            println("Value = $value")
//        }
//    }catch(e: Throwable){
//        println("Catch $e")
//    }
//}

// hay throw Ex trong nguồn thu

//fun foo(): Flow<Int> = flow{
//    for(i in 3 downTo -3){
//        emit(i)
//    }
//}
//fun main() = runBlocking {
//    try {
//        foo().collect { value ->
//            println("3/$value = ${3/value}")
//        }
//    }catch(e: Throwable){
//        println("Catch $e")
//    }
//}

//try/catch vẫn catch được dù Exception có xảy ra trong nguồn thu hay nguồn phát

//toán tử catch
//fun foo(): Flow<String> = flow{
//    for(i in 3 downTo -3){
//        println("3/$i = ${3/i}")
//        emit(i.toString())
//    }
//}
//fun main() = runBlocking {
//        foo().catch {e -> emit("caught $e")}
//            .collect { value ->
//            println("3/$value = ${3 / value}")
//
//        }
//}

//emit exception đó đến nguồn thu còn try/catch thì không thể làm được điều này.
// tuy nhiên catch lại k thể catch exception xảy ra trong hàm collect{} như try/catch

//fun foo(): Flow<Int> = flow{
//    for(i in 3 downTo -3){
//        emit(i)
//    }
//}
//fun main() = runBlocking {
//        foo().catch {e -> println("caught $e")}
//            .collect { value ->
//            println("3/$value = ${3 / value}")
//        }
//}

// để catch cả trong nguồn thu , ta dùng onEach()
// ta sẽ move code trong hàm collect (nơi xảy ra Exception) vào toán tử onEach. Đồng thời hàm collect sẽ không còn param nào nữa

//fun foo(): Flow<Int> = flow{
//    for(i in 3 downTo -3){
//        emit(i)
//    }
//}
//fun main() = runBlocking {
//        foo().onEach{ value ->
//            println("3/$value = ${3 / value}")
//        }.catch {e-> println("Caught $e")}
//            .collect()
//}

// nếu ta dùng collect sau onEach thì code sau nó sẽ đợi cho đến khi flow kết thúc việc collect rồi mới chạy.

//fun events(): Flow<Int> = (1..3).asFlow().onEach { delay(100) }
//fun main() = runBlocking<Unit> {
//    events()
//        .onEach { event -> println("Event: $event") }
//        .collect{}
//    println("Done")
//}

// dòng Done phải đợi flow kết thúc mới được chạy.
// vậy để k muốn phải đợi mà muốn coroutine vẫn chạy xuống code dưới dù có delay hay collect.
// ta dùng toán tử lauchIn
// Toán tử này truyền vào một param là CoroutineScope và return một biến Job.
// Biến job này có thể giúp chúng ta cancel code trong flow mà không cancel hết cả coroutine.
// Code trong coroutine vẫn tiếp tục chạy.

//fun events(): Flow<Int> = (1..3).asFlow().onEach { delay(100) }
//fun main() = runBlocking<Unit> {
//    events()
//        .onEach { event -> println("Event: $event") }
//        .launchIn(this) // lỗi
//    println("Done")
//}

//fun testLaunchException() = runBlocking {
//    launch {
//        println("Throwing exception from launch")
//        throw IndexOutOfBoundsException()
//        println("Unreached")
//    }
//}
//
//fun main() = runBlocking<Unit> {
//    testLaunchException()
//}

//***********************************************************************

// Flow Completion
// ta muốn biết thời điểm flow vừa kết thúc tiến trình , có 2 cách
//* dùng finally

//fun foo(): Flow<Int> = (1..3).asFlow()
//fun main() = runBlocking<Unit> {
//    try {
//        foo().collect { value -> println(value) }
//    }finally {
//        println("Done")
//    }
//}

//* dùng onCompletion

//fun foo(): Flow<Int> = (1..3).asFlow()
//fun main() = runBlocking<Unit> {
//        foo()
//            .onCompletion{println("Done")}        // lỗi k có oncompletion
//            .collect{ value -> println(value) }
//}

// sau khi tiến trình flow kết thúc nó sẽ chạy vào code trong khối onCompletion.
// Và chúng ta có thể tận dụng chỗ này để hide progressBar chẳng hạn
// Một ưu điểm nữa của toán tử onCompletion là nó có thể giúp ta biết được flow đã kết thúc tiến trình chuẩn xác hay kết thúc bằng một Exception
// Chúng ta có thể dễ dàng biết được thông qua param nullable Throwable?.
// Nếu param này mà null thì flow đã kết thúc tiến trình chuẩn xác, nếu param này khác null thì Exception đã xảy ra

//fun foo(): Flow<Int> = flow{
//    emit(1)
//    throw RuntimeException()
//}
//fun main() = runBlocking<Unit> {
//    foo()
//        .onCompletion{ cause -> if(cause != null) println("Flow completed exceptionally")}
//        .catch {cause -> println("Caught exception")}
//        .collect{value -> println(value)}
//}


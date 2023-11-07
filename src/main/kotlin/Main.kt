import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import threadrealisation.arrayOfNumbers
import threadrealisation.arrayOfNumbersAs
import threadrealisation.readNumbers
import threadrealisation.readNumbersAs

fun main() = runBlocking {
    val time = System.currentTimeMillis()
    val result = performOperation()
    val singleThreadTimeResult = (System.currentTimeMillis() - time)
    println("Execution time in single thread: $singleThreadTimeResult milliseconds")

    val timeAsync = System.currentTimeMillis()
    val asyncResult = performOperationAsync()
    val asyncTimeResult = (System.currentTimeMillis() - timeAsync)
    println("Execution time is asynchronous: $asyncTimeResult milliseconds")

    if (result == asyncResult) {
        println("The answer is correct: $result == $asyncResult")
    } else {
        println("The answer is incorrect: $result != $asyncResult")
    }

    if (asyncTimeResult <= singleThreadTimeResult) {
        println("The asynchronous version is faster: $asyncTimeResult milliseconds")
    } else {
        println("The synchronous version is faster: $singleThreadTimeResult milliseconds")
    }
}

fun performOperation(): Int {
    readNumbers()
    var sum = 0
    arrayOfNumbers.forEach { e -> sum += e }
    println(sum)

    return sum
}

suspend fun performOperationAsync(): Int = coroutineScope {
    println("\n" + "Start of asynchronous implementation\n" + "-------------------------------------------------------------- " + "\n"
    )
    readNumbersAs()
    var sumAsync = 0
    arrayOfNumbersAs.forEach { e -> sumAsync += e }
    println(sumAsync)

    return@coroutineScope sumAsync
}

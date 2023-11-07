package threadrealisation

import count
import kotlinx.coroutines.*
import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException

val arrayOfNumbersAs = ArrayList<Int>()

suspend fun readNumbersAs() = coroutineScope {
    try {
        for (i in 1..count) {
            launch(Dispatchers.Default) {
                val fileReader = withContext(Dispatchers.IO) {
                    FileReader("$i.txt")
                }
                val bufferedReader = BufferedReader(fileReader)
                val rows = bufferedReader.lines()

                var sum = 0
                rows.forEach { e ->
                    if (e != null) sum += e.toInt()
                }
                withContext(Dispatchers.IO) {
                    bufferedReader.close()
                }
                arrayOfNumbersAs.add(sum)

                println("Filename: $i\nSum is: $sum \n ${Thread.currentThread().name}\n")
            }
        }

    } catch (e: IOException) {
        println("Error reading file")
        e.printStackTrace()

    }
}

fun main() = runBlocking {
    val time = System.currentTimeMillis()

    readNumbersAs()

    val sum = arrayOfNumbersAs.sum()
    println(sum)

    println((System.currentTimeMillis() - time).toString() + " millis")
}

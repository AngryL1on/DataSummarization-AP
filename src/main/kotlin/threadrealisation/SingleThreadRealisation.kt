package threadrealisation

import count
import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException

val arrayOfNumbers = ArrayList<Int>()

fun readNumbers() {
    try {
        for (i in 1..count) {
            val fileReader = FileReader("$i.txt")
            val bufferedReader = BufferedReader(fileReader)
            val rows = bufferedReader.lines()
            println("Filename: $i")

            var sum = 0
            rows.forEach { e ->
                if (e != null) sum += e.toInt()
            }
            bufferedReader.close()

            arrayOfNumbers.add(sum)
            println("Sum is: $sum \n ${Thread.currentThread().name}\n")
        }

    } catch (e: IOException) {
        println("Error reading file")
        e.printStackTrace()
    }
}

//fun readDigits() {
//    try {
//        for (i in 1..count) {
//            val fileReader = FileReader("$i.txt")
//            val sum = fileReader.use { reader ->
//                reader.readLines()
//                    .sumOf { it.toInt() }
//            }
//            arrayOfDigits.add(sum)
//            println("Filename: $i")
//            println("Sum is: $sum \n ${Thread.currentThread().name}")
//        }
//    } catch (e: IOException) {
//        println("Error reading file")
//        e.printStackTrace()
//    }
//}

fun main() {
    val time = System.currentTimeMillis()

    readNumbers()

    val sum = arrayOfNumbers.sum()
    println(sum)

    println((System.currentTimeMillis() - time).toString() + " millis")
}

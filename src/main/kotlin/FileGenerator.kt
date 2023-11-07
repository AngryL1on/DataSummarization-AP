import java.io.BufferedWriter
import java.io.FileWriter
import kotlin.random.Random

const val count: Int = 19
const val rows: Int = 10000000

fun generateFiles(count: Int, rows: Int) {
    for (i in 1..count) {
        val name = i.toString()
        val fileWriter = FileWriter("$name.txt")
        val bufferedWriter = BufferedWriter(fileWriter)
        for (j in 1..rows) {
            bufferedWriter.write(Random.nextInt(-10, 10).toString() + "\n")
        }
        bufferedWriter.close()
    }
}

fun main() {
    generateFiles(count, rows)
}

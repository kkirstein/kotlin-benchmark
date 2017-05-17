/* benchmark.kt
 * A set of (micro-) benchmarks for the Kotlin
 * (http://kotlinlang.org) programming language
 */

package benchmark

import kotlinx.coroutines.experimental.*
//import benchmark.timeit.timeIt
import benchmark.experimental.timeit.timeItAsync
import benchmark.fibonacci.*
import benchmark.mandelbrot.mandelbrot
import benchmark.perfectnumber.*
import java.io.File
import javax.imageio.ImageIO

// main entry point
fun main(args: Array<String>) = runBlocking {

    println("Kotlin benchmarks")
    println("=================")
    println()

    // Fibonacci numbers
    // -----------------
    val resFibNaive = timeItAsync { fibNaive(35) }
    val resFib = timeItAsync { fib(35) }
    val resFib2 = timeItAsync { fib(1000) }

    // Perfect numbers
    // ---------------
    val resPn = timeItAsync { perfectNumbers(10000) }
    val resPnSeq = timeItAsync { perfectNumberSeq.take(5).toList() }

    // Output results
    println("Fibonacci numbers")
    println("-----------------")
    println("fibNaive(35) = ${resFibNaive.await().result}, elapsed time: ${resFibNaive.await().elapsed} ms.")
    println("fib(35) = ${resFib.await().result}, elapsed time: ${resFib.await().elapsed} ms.")
    println("fib(1000) = ${resFib2.await().result}, elapsed time: ${resFib2.await().elapsed} ms.")

    println()

    println("Perfect numbers")
    println("---------------")
    println("perfectNumbers(10000) = ${resPn.await().result}, elapsed time: ${resPn.await().elapsed} ms.")
    println("perfectNumberSeq(5) = ${resPnSeq.await().result}, elapsed time: ${resPnSeq.await().elapsed} ms.")

    println()

    println("Mandelbrot set")
    println("--------------")
    val ms = mandelbrot(800, 600, -0.0, -0.65)
    val mandelbrotFile = File("./mandelbrot.png")
    ImageIO.write(ms, "png", mandelbrotFile)

    println("------------------")
    println("Done.")
}

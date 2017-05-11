/* benchmark.kt
 * A set of (micro-) benchmarks for the Kotlin
 * (http://kotlinlang.org) programming language
 */

package benchmark

import kotlinx.coroutines.experimental.*
import benchmark.timeit.timeIt
import benchmark.experimental.timeit.timeIt as timeItAsync
import benchmark.fibonacci.*
import benchmark.experimental.fibonacci.fibNaive as fibNaiveAsync
import benchmark.perfectnumber.*

// main entry point
fun main(args: Array<String>) = runBlocking {

    println("Kotlin benchmarks")
    println("=================")
    println()

    var jobs: MutableList<Job> = mutableListOf()

    // Fibonacci numbers
    // -----------------
    jobs.add(launch(CommonPool) {
        val res = timeItAsync { fibNaiveAsync(35) }
        println("fibNaive(35) = ${res.result}, elapsed time: ${res.elapsed} ms.")
    })
    jobs.add(launch(CommonPool) {
        val res = timeItAsync { fib(35) }
        println("fib(35) = ${res.result}, elapsed time: ${res.elapsed} ms.")
    })
    jobs.add(launch(CommonPool) {
        val res = timeItAsync { fib(1000) }
        println("fib(1000) = ${res.result}, elapsed time: ${res.elapsed} ms.")
    })

    // Perfect numbers
    // ---------------
    jobs.add(launch(CommonPool) {
        val res = timeItAsync { perfectNumbers(10000) }
        println("perfectNumbers(10000) = ${res.result}, elapsed time: ${res.elapsed} ms.")
    })
    jobs.add(launch(CommonPool) {
        val res = timeItAsync { perfectNumberSeq.take(5).toList() }
        println("perfectNumberSeq(5) = ${res.result}, elapsed time: ${res.elapsed} ms.")
    })

    //println("------------------")
    jobs.map { it.join() }
    println("Done.")

}

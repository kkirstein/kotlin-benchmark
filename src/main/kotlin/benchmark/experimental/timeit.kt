/* timeit.kt
 * Package to generate timing infos of function application
 * in the Kotlin (http://kotlinlang.org) programming language
 */

package benchmark.experimental.timeit

import kotlinx.coroutines.experimental.*
//import kotlin.coroutines.experimental.*

import benchmark.timeit.timeIt
import benchmark.timeit.Result

// container for timing and function result
data class Result<T>(val result: T, val elapsed: Long)

// call given function and record elapsed timeit (suspendable version)
/* suspend fun <T> timeIt(body: suspend () -> T): Result<T> {
  val tic = System.currentTimeMillis()
  val res: T = body()
  val toc = System.currentTimeMillis()

  return Result<T>(res, toc-tic)
} */

// async version of timeIt
fun <T> timeItAsync(body: () -> T): Deferred<Result<T>> {
  return async(CommonPool) { timeIt(body) }
}
/* timeit.kt
 * Package to generate timing infos of function application
 * in the Kotlin (http://kotlinlang.org) programming language
 */

package benchmark.experimental.timeit

import kotlin.coroutines.experimental.*

// container for timing and function result
data class Result<T>(val result: T, val elapsed: Long)

// call given function and record elapsed timeit (suspendable version)
suspend fun <T> timeIt(body: suspend () -> T): Result<T> {
  val tic = System.currentTimeMillis()
  val res: T = body()
  val toc = System.currentTimeMillis()

  return Result<T>(res, toc-tic)
}

/* timeit.kt
 * Package to generate timing infos of function application
 * in the Kotlin (http://kotlinlang.org) programming language
 */

package benchmark.timeit

import kotlin.coroutines.experimental.*

// container for timing and function result
data class Result<T>(val result: T, val elapsed: Long)

// call given function and record elapsed timeit
fun <T> timeIt(body: () -> T): Result<T> {
  val tic = System.currentTimeMillis()
  val res: T = body()
  val toc = System.currentTimeMillis()

  return Result<T>(res, toc-tic)
}

suspend fun <T> timeIt2(body: suspend () -> T): Result<T> {
  val tic = System.currentTimeMillis()
  val res: T = body()
  val toc = System.currentTimeMillis()

  return Result<T>(res, toc-tic)
}

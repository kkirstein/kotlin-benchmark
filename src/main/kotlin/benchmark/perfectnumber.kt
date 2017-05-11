/* perfectnumber.kt
 * Find perfect numbers in the Kotlin
 * (http://kotlinlang.org) programming language
 */

package benchmark.perfectnumber

import kotlin.coroutines.experimental.*

/* predicate to check whether a given number is perfect
 * meaning it is equal to th esum of its dividends
 */
fun isPerfect(n: Int): Boolean {

  var sum = 0
  var i = 1
  do {
    if (n % i == 0) sum += i
    i++
  } while (i < n)

  return sum == n
}

/* find all perfect numbers smaller than given limit
 */
fun perfectNumbers(n: Int): List<Int> {
  return 1.rangeTo(n).filter { isPerfect(it) }
}

/* a lazy sequence of perfect numbers
 */
val perfectNumberSeq = buildSequence {
  var current = 1
  while (true) {
    if (isPerfect(current)) yield(current)
    current++
  }
}

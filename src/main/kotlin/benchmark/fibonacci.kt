/* fibonacci.kt
 * Calculate Fibonacci numbers in the Kotlin
 * (http://kotlinlang.org) programming language
 */

package benchmark.fibonacci

import java.math.BigInteger

// naive implementation
fun fibNaive(n: Int): Int {
  if (n < 2) {
    return n
  } else {
    return (fibNaive(n-1) + fibNaive(n-2))
  }
}

// optimized tail-recursive implementation
fun fib(n: Int): BigInteger {
  tailrec fun loop(a: BigInteger, b: BigInteger, n: Int): BigInteger {
    return if (n == 0) a else loop(b, a + b, n - 1)
  }

  return loop(BigInteger.valueOf(0), BigInteger.valueOf(1), n)
}

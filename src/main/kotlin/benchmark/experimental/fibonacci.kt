/* fibonacci.kt
 * Calculate Fibonacci numbers in the Kotlin
 * (http://kotlinlang.org) programming language
 *
 * This package uses experimental features like coroutines
 */

package benchmark.experimental.fibonacci

import benchmark.fibonacci.fibNaive

// suspendable version of fibNaive
suspend fun fibNaive(n: Int): Int = fibNaive(n)

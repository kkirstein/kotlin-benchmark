/* complex.kt
* Complex numbers in Kotlin
* (http://kotlinlang.org) programming language
*/

package complex

data class Complex(val re: Double, val im: Double) {

  companion object {
    val zero = Complex(0.0, 0.0)
  }

  fun reciprocal(): Complex {
    val scale = (re * re) + (im * im)
    return Complex(re / scale, -im / scale)
  }

  fun abs(): Double = Math.hypot(re, im)

  operator fun unaryMinus(): Complex = Complex(-re, -im)
  operator fun plus(other: Double): Complex = Complex(re + other, im)
  operator fun minus(other: Double): Complex = Complex(re - other, im)
  operator fun times(other: Double): Complex = Complex(re * other, im * other)
  operator fun div(other: Double): Complex = Complex(re / other, im / other)

  operator fun plus(other: Complex): Complex =
    Complex(re + other.re, im + other.im)

  operator fun minus(other: Complex): Complex =
    Complex(re - other.re, im - other.im)

  operator fun times(other: Complex): Complex =
    Complex(
      (re * other.re) - (im * other.im),
      (re * other.im) + (im * other.re))

  operator fun div(other: Complex): Complex = this * other.reciprocal()

}

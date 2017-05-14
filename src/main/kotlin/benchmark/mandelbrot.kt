/* mandelbrot.kt
 * Calculate Mandelbrot sets in the Kotlin
 * (http://kotlinlang.org) programming language
 */

package benchmark.mandelbrot

import java.awt.image.BufferedImage
import java.nio.Buffer

import complex.Complex

// first we need an container for the image data
data class Image<T>(val width: Int, val height: Int, val channels: Int,
                    val data: Array<T>)

// calculates pixel values
fun pixelVal(z0: Complex, nIter: Int = 255, zMax: Double = 2.0): Int {
    var iter = 0
    var z = Complex.zero

    while (iter <= nIter) {
        if (z.abs() > zMax) return iter
        z = z * z + z0
        iter++
    }

    return nIter
}

// generates Mandelbrot set for given coordinates
fun mandelbrot(width: Int, height: Int,
               xCenter: Double, yCenter: Double,
               pixSize: Double = 4.0 / width): BufferedImage {

    val img = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)

    return img
}

// prints an ASCII Mandelbrot set
/*for (i in -40.0..40.0) {
    for (r in -40.0..40.0) {
        print(mandelbrot(Complex(r - 25.0, i) / 35.0, 256)
                ?.let { 'a' + (it % 26) }
                ?: ' '
        )
    }
    println()
  }*/

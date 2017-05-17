/* mandelbrot.kt
 * Calculate Mandelbrot sets in the Kotlin
 * (http://kotlinlang.org) programming language
 */

package benchmark.mandelbrot

import java.awt.image.BufferedImage

import complex.Complex

// first we need an container for the image data
data class Image<T>(val width: Int, val height: Int, val channels: Int,
                    val data: Array<T>)

// calculates pixel values
fun pixelVal(z0: Complex, maxIter: Int = 255, zMax: Double = 2.0): Int {
    var iter = 0
    var z = Complex.zero

    while (iter <= maxIter) {
        if (z.abs() > zMax) return iter
        z = z * z + z0
        iter++
    }

    return maxIter
}

// convert pixel value to RGB
fun toRGB(value: Int) =
    if (value == 0) {
        0
    } else {
        val r = 5 * (value % 15)
        val g = 32 * (value % 7)
        val b = 8 *(value % 31)
        (r.shl(16) or g.shl(8) or b)
    }

// generates Mandelbrot set for given coordinates
fun mandelbrot(width: Int, height: Int,
               xCenter: Double, yCenter: Double,
               pixSize: Double = 4.0 / width): BufferedImage {

    val img = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)

    for (y in 0.rangeTo(height-1)) {
        for (x in 0.rangeTo(width-1)) {
            val xCoord = xCenter - 0.5 * x.toDouble() * pixSize
            val yCoord = yCenter + 0.5 * y.toDouble() * pixSize
            val pixVal = pixelVal(Complex(re = xCoord, im = yCoord))

            img.setRGB(x, y, pixVal)
        }
    }
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

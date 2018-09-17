/* mandelbrot.kt
 * Calculate Mandelbrot sets in the Kotlin
 * (http://kotlinlang.org) programming language
 */

package benchmark.mandelbrot

import java.awt.image.BufferedImage

import complex.Complex
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async

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

// calculate a single line of Mandelbrot set
fun mandelbrotLine(y: Int, width: Int,
                   xCenter: Double, yCenter: Double,
                   pixSize: Double = 4.0 / width): IntArray {

    val imgLine = IntArray(width)

    val yCoord = yCenter + 0.5 * y.toDouble() * pixSize
    for (x in 0.rangeTo(width-1)) {
        val xCoord = xCenter - 0.5 * x.toDouble() * pixSize
        val pixVal = toRGB(pixelVal(Complex(re = xCoord, im = yCoord)))
        imgLine[x] = pixVal
    }

    return imgLine
}

// generates Mandelbrot set for given coordinates
suspend fun mandelbrot(width: Int, height: Int,
               xCenter: Double, yCenter: Double,
               pixSize: Double = 4.0 / width): BufferedImage {

    val img = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
    //val imgData = Array<Int>(width*height) { _ -> 0 }
    val imgData = IntArray(width*height)
    //var imgDataAsync = Array<Deferred<Int>>(0)

    for (y in 0.rangeTo(height-1)) {
        val imgLine = async(CommonPool) { mandelbrotLine(y, width, xCenter, yCenter, pixSize) }
        img.setRGB(0, y, width, 1, imgLine.await(), 0, width)
    }

    //img.setRGB(0, 0, width, height, imgData, 0, width)
    return img
}

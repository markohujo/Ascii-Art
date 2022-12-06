package filters

import app.models.pixel.GreyscalePixel
import app.models.{Image, PixelGrid}
import filters.image.greyscale.BrightnessImageFilter
import org.scalatest.FunSuite

class BrightnessFilterTest extends FunSuite {

  private val grayscaleImage = Image(PixelGrid(Seq(
    Seq(GreyscalePixel(255), GreyscalePixel(0), GreyscalePixel(100)),
    Seq(GreyscalePixel(255), GreyscalePixel(0), GreyscalePixel(100)),
  )))

  test("test brightness filter + 10") {
    testBrightness(10)
  }

  test("test brightness filter +255") {
    testBrightness(255)
  }

  test("test brightness filter +300") {
    testBrightness(300)
  }

  test("test brightness filter -10") {
    testBrightness(-10)
  }

  test("test brightness filter -255") {
    testBrightness(-255)
  }

  test("test brightness filter -300") {
    testBrightness(-300)
  }

  test("test brightness filter identity") {
    testBrightness(0)
  }

  private def testBrightness(value: Int): Unit = {
    val filter = new BrightnessImageFilter(value)
    val invertedImage = filter.apply(grayscaleImage)

    if (value == 0) {
      assert(grayscaleImage == invertedImage)
      return
    }

    for (i <- 0 until grayscaleImage.height) {
      for (j <- 0 until grayscaleImage.width) {
        if (value > 0)
          assert(Math.min(grayscaleImage.pixelAt(i, j).value + value, 255) == invertedImage.pixelAt(i, j).value)
        else
          assert(Math.max(grayscaleImage.pixelAt(i, j).value + value, 0) == invertedImage.pixelAt(i, j).value)
      }
    }
  }
}

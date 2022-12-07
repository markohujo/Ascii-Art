package filters.image

import app.models.pixel.GreyscalePixel
import app.models.{Image, PixelGrid}
import filters.image.greyscale.BrightnessImageFilter
import org.scalatest.FunSuite

class BrightnessFilterTest extends FunSuite {

  private val greyscaleImage = Image(PixelGrid(Seq(
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
    val filter = new BrightnessImageFilter(0)
    val updatedImage = filter.apply(greyscaleImage)
    assert(greyscaleImage == updatedImage)
  }

  test("test brightness + brightness") {
    val values = (10, 15)
    var filter = new BrightnessImageFilter(values._1)
    var updatedImage = filter.apply(greyscaleImage)
    testBrightness(values._1, updatedImage)

    filter = new BrightnessImageFilter(values._2)
    updatedImage = filter.apply(updatedImage)
    testBrightness(values._1 + values._2, updatedImage)
  }

  private def testBrightness(value: Int): Unit = {
    val filter = new BrightnessImageFilter(value)
    val updatedImage = filter.apply(greyscaleImage)

    if (value == 0) {
      assert(greyscaleImage == updatedImage)
      return
    }

    testBrightness(value, updatedImage)
  }

  private def testBrightness(value: Int, updatedImage: Image[GreyscalePixel]): Unit = {
    for (i <- 0 until greyscaleImage.height) {
      for (j <- 0 until greyscaleImage.width) {
        if (value > 0)
          assert(Math.min(greyscaleImage.pixelAt(i, j).value + value, 255) == updatedImage.pixelAt(i, j).value)
        else
          assert(Math.max(greyscaleImage.pixelAt(i, j).value + value, 0) == updatedImage.pixelAt(i, j).value)
      }
    }
  }
}

package filters

import app.models.{Image, PixelGrid}
import app.models.pixel.{AsciiPixel, GrayscalePixel}
import filters.image.ascii.RotateImageFilter
import filters.image.grayscale.{BrightnessImageFilter, InvertImageFilter}
import org.scalatest.FunSuite

class ImageFilterTest extends FunSuite {

  private val asciiImage = Image(PixelGrid(Seq(
    Seq(AsciiPixel('A'), AsciiPixel('B'), AsciiPixel('C')),
    Seq(AsciiPixel('D'), AsciiPixel('E'), AsciiPixel('F')),
  )))

  private val grayscaleImage = Image(PixelGrid(Seq(
    Seq(GrayscalePixel(255), GrayscalePixel(0), GrayscalePixel(100)),
    Seq(GrayscalePixel(255), GrayscalePixel(0), GrayscalePixel(100)),
  )))

  test("test brightness filter") {
    testBrightness(10)
    testBrightness(255)
    testBrightness(300)
    testBrightness(-10)
    testBrightness(-255)
    testBrightness(-300)
    testBrightness(0)
  }

  test("test invert filter") {
    val filter = new InvertImageFilter
    val invertedImage = filter.apply(grayscaleImage)
    for (i <- 0 until grayscaleImage.height) {
      for (j <- 0 until grayscaleImage.width) {
        assert(grayscaleImage.pixelAt(i, j).value == 255 - invertedImage.pixelAt(i, j).value)
      }
    }
  }

  test("test rotate 0") {
    val filter = new RotateImageFilter(0)
    val rotatedImage = filter.apply(asciiImage)
    assert(asciiImage == rotatedImage)
  }

  test("test rotate 90") {
    var filter = new RotateImageFilter(90)
    val rotatedImage = filter.apply(asciiImage)
    assert(rotatedImage.height == asciiImage.width && rotatedImage.width == asciiImage.height)
    assert(rotatedImage.pixelAt(0, 0).value == 'D')
    assert(rotatedImage.pixelAt(0, 1).value == 'A')
    assert(rotatedImage.pixelAt(1, 0).value == 'E')
    assert(rotatedImage.pixelAt(1, 1).value == 'B')
    assert(rotatedImage.pixelAt(2, 0).value == 'F')
    assert(rotatedImage.pixelAt(2, 1).value == 'C')

    filter = new RotateImageFilter(-270)
    val rotatedImage2 = filter.apply(asciiImage)
    assert(rotatedImage == rotatedImage2)

    filter = new RotateImageFilter(450)
    val rotatedImage3 = filter.apply(asciiImage)
    assert(rotatedImage == rotatedImage3)
  }

  test("test rotate 180") {
    var filter = new RotateImageFilter(180)
    val rotatedImage = filter.apply(asciiImage)
    assert(rotatedImage.height == asciiImage.height && rotatedImage.width == asciiImage.width)
    assert(rotatedImage.pixelAt(0, 0).value == 'F')
    assert(rotatedImage.pixelAt(0, 1).value == 'E')
    assert(rotatedImage.pixelAt(0, 2).value == 'D')
    assert(rotatedImage.pixelAt(1, 0).value == 'C')
    assert(rotatedImage.pixelAt(1, 1).value == 'B')
    assert(rotatedImage.pixelAt(1, 2).value == 'A')

    filter = new RotateImageFilter(-180)
    val rotatedImage2 = filter.apply(asciiImage)
    assert(rotatedImage == rotatedImage2)

    filter = new RotateImageFilter(540)
    val rotatedImage3 = filter.apply(asciiImage)
    assert(rotatedImage == rotatedImage3)
  }

  test("test rotate 270") {
    var filter = new RotateImageFilter(270)
    val rotatedImage = filter.apply(asciiImage)
    assert(rotatedImage.height == asciiImage.width && rotatedImage.width == asciiImage.height)
    assert(rotatedImage.pixelAt(0, 0).value == 'C')
    assert(rotatedImage.pixelAt(0, 1).value == 'F')
    assert(rotatedImage.pixelAt(1, 0).value == 'B')
    assert(rotatedImage.pixelAt(1, 1).value == 'E')
    assert(rotatedImage.pixelAt(2, 0).value == 'A')
    assert(rotatedImage.pixelAt(2, 1).value == 'D')

    filter = new RotateImageFilter(-90)
    val rotatedImage2 = filter.apply(asciiImage)
    assert(rotatedImage == rotatedImage2)

    filter = new RotateImageFilter(630)
    val rotatedImage3 = filter.apply(asciiImage)
    assert(rotatedImage == rotatedImage3)
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

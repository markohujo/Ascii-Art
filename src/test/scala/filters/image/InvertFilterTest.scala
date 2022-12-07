package filters.image

import app.models.pixel.GreyscalePixel
import app.models.{Image, PixelGrid}
import filters.image.greyscale.InvertImageFilter
import org.scalatest.FunSuite

class InvertFilterTest extends FunSuite {

  private val greyscaleImage = Image(PixelGrid(Seq(
    Seq(GreyscalePixel(255), GreyscalePixel(0), GreyscalePixel(100)),
    Seq(GreyscalePixel(255), GreyscalePixel(0), GreyscalePixel(100)),
  )))

  test("test invert filter") {
    val filter = new InvertImageFilter
    val invertedImage = filter.apply(greyscaleImage)
    testInvert(invertedImage)
  }

  test("test invert + invert is identity") {
    val filter = new InvertImageFilter
    var invertedImage = filter.apply(greyscaleImage)
    invertedImage = filter.apply(invertedImage)
    assert(greyscaleImage == invertedImage)
  }

  test("test invert 3 times is same as once") {
    val filter = new InvertImageFilter
    var invertedImage = filter.apply(greyscaleImage)
    invertedImage = filter.apply(invertedImage)
    assert(greyscaleImage == invertedImage)
    invertedImage = filter.apply(invertedImage)
    testInvert(invertedImage)
  }

  private def testInvert(invertedImage: Image[GreyscalePixel]): Unit = {
    for (i <- 0 until greyscaleImage.height) {
      for (j <- 0 until greyscaleImage.width) {
        assert(greyscaleImage.pixelAt(i, j).value == 255 - invertedImage.pixelAt(i, j).value)
      }
    }
  }
}

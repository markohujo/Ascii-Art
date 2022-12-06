package filters.image

import app.models.pixel.GreyscalePixel
import app.models.{Image, PixelGrid}
import filters.image.greyscale.InvertImageFilter
import org.scalatest.FunSuite

class InvertFilterTest extends FunSuite {

  private val grayscaleImage = Image(PixelGrid(Seq(
    Seq(GreyscalePixel(255), GreyscalePixel(0), GreyscalePixel(100)),
    Seq(GreyscalePixel(255), GreyscalePixel(0), GreyscalePixel(100)),
  )))

  test("test invert filter") {
    val filter = new InvertImageFilter
    val invertedImage = filter.apply(grayscaleImage)
    for (i <- 0 until grayscaleImage.height) {
      for (j <- 0 until grayscaleImage.width) {
        assert(grayscaleImage.pixelAt(i, j).value == 255 - invertedImage.pixelAt(i, j).value)
      }
    }
  }

}

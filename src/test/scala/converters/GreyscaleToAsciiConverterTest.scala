package converters

import app.models.pixel.{AsciiPixel, GreyscalePixel}
import app.models.{Image, PixelGrid}
import converters.pixel.{GreyscaleToAsciiConverter, LinearGreyscaleToAsciiConverter}
import org.scalatest.FunSuite

trait GreyscaleToAsciiConverterTest[T <: GreyscaleToAsciiConverter] extends FunSuite {

  private val image = Image(PixelGrid(Seq(
    Seq(GreyscalePixel(255), GreyscalePixel(151), GreyscalePixel(100)),
    Seq(GreyscalePixel(202), GreyscalePixel(0), GreyscalePixel(37))
  )))

  protected var converter: T = _

  protected def correctAsciiValue(value: Int): AsciiPixel

  test("greyscale to ascii converter test") {
    val convertedImage = image.transform(converter.convert)

    for (i <- 0 until convertedImage.height) {
      for (j <- 0 until convertedImage.width) {
        assert(correctAsciiValue(image.pixelAt(i, j).value) == convertedImage.pixelAt(i, j))
      }
    }
  }
}

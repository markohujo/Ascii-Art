package converters

import app.models.pixel.{GreyscalePixel, RgbPixel}
import app.models.{Image, PixelGrid}
import converters.pixel.RgbToGrayscaleConverter
import org.scalatest.FunSuite

import java.awt.Color

class RgbToGrayscaleConverterTest extends FunSuite {

  private val image = Image(PixelGrid(Seq(
    Seq(RgbPixel(Color.BLACK), RgbPixel(Color.GREEN)),
    Seq(RgbPixel(Color.RED), RgbPixel(Color.BLUE)),
    Seq(RgbPixel(Color.WHITE), RgbPixel(Color.GRAY)),
    Seq(RgbPixel(Color.ORANGE), RgbPixel(Color.CYAN)),
  )))

  private val converter = new RgbToGrayscaleConverter

  test("rgb to grayscale converter test") {
    val convertedImage = image.transform(converter.convert)
    assert(convertedImage.pixelAt(0, 0).value == 0)
    assert(convertedImage.pixelAt(0, 1).value == 150)
    assert(convertedImage.pixelAt(1, 0).value == 76)
    assert(convertedImage.pixelAt(1, 1).value == 28)
    assert(convertedImage.pixelAt(2, 0).value == 255)
    assert(convertedImage.pixelAt(2, 1).value == 127)
    assert(convertedImage.pixelAt(3, 0).value == 194)
    assert(convertedImage.pixelAt(3, 1).value == 178)
  }
}

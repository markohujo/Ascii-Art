package converters

import app.models.{Image, PixelGrid}
import app.models.pixel.GreyscalePixel
import converters.pixel.NonLinearGreyscaleToAsciiConverter
import org.scalatest.FunSuite

class NonLinearGreyscaleToAsciiConverterTest extends FunSuite {

  private val greyscaleImage = Image(PixelGrid(Seq(
    Seq(GreyscalePixel(255), GreyscalePixel(0), GreyscalePixel(100)),
    Seq(GreyscalePixel(200), GreyscalePixel(30), GreyscalePixel(75))
  )))

  private val converter = new NonLinearGreyscaleToAsciiConverter

  test("nonlinear greyscale to ascii converter test") {
    val asciiImage = greyscaleImage.transform(converter.convert)
    assert(asciiImage.pixelAt(0, 0).value == '@')
    assert(asciiImage.pixelAt(0, 1).value == ' ')
    assert(asciiImage.pixelAt(0, 2).value == ':')
    assert(asciiImage.pixelAt(1, 0).value == '@')
    assert(asciiImage.pixelAt(1, 1).value == ' ')
    assert(asciiImage.pixelAt(1, 2).value == '.')
  }
}

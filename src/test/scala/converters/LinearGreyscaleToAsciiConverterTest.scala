package converters

import app.models.{Image, PixelGrid}
import app.models.pixel.GreyscalePixel
import converters.pixel.LinearGreyscaleToAsciiConverter
import org.scalatest.FunSuite
import utils.Constants

class LinearGreyscaleToAsciiConverterTest extends FunSuite {

  private val greyscaleImage = Image(PixelGrid(Seq(
    Seq(GreyscalePixel(255), GreyscalePixel(0), GreyscalePixel(100)),
    Seq(GreyscalePixel(200), GreyscalePixel(30), GreyscalePixel(75))
  )))

  test("empty linear greyscale to ascii converter test") {
    assertThrows[IllegalArgumentException] {
      new LinearGreyscaleToAsciiConverter("")
    }
  }

  test("blank linear greyscale to ascii converter test") {
    assertThrows[IllegalArgumentException] {
      new LinearGreyscaleToAsciiConverter("   ")
    }
  }

  test("default linear greyscale to ascii converter test") {
    val converter = new LinearGreyscaleToAsciiConverter
    val asciiImage = greyscaleImage.transform(converter.convert)
    assert(asciiImage.pixelAt(0, 0).value == ' ')
    assert(asciiImage.pixelAt(0, 1).value == '$')
    assert(asciiImage.pixelAt(0, 2).value == 'U')
    assert(asciiImage.pixelAt(1, 0).value == '~')
    assert(asciiImage.pixelAt(1, 1).value == '#')
    assert(asciiImage.pixelAt(1, 2).value == 'Z')
  }

  test("default simple linear greyscale to ascii converter test") {
    val converter = new LinearGreyscaleToAsciiConverter(Constants.DefaultSimplifiedTransformationTable)
    val asciiImage = greyscaleImage.transform(converter.convert)
    assert(asciiImage.pixelAt(0, 0).value == '@')
    assert(asciiImage.pixelAt(0, 1).value == ' ')
    assert(asciiImage.pixelAt(0, 2).value == '-')
    assert(asciiImage.pixelAt(1, 0).value == '#')
    assert(asciiImage.pixelAt(1, 1).value == '.')
    assert(asciiImage.pixelAt(1, 2).value == ':')
  }

  test("custom linear greyscale to ascii converter test") {
    val converter = new LinearGreyscaleToAsciiConverter(" .:@#")
    val asciiImage = greyscaleImage.transform(converter.convert)
    assert(asciiImage.pixelAt(0, 0).value == '#')
    assert(asciiImage.pixelAt(0, 1).value == ' ')
    assert(asciiImage.pixelAt(0, 2).value == '.')
    assert(asciiImage.pixelAt(1, 0).value == '@')
    assert(asciiImage.pixelAt(1, 1).value == ' ')
    assert(asciiImage.pixelAt(1, 2).value == '.')
  }
}

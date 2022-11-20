package converters

import app.models.pixel.AsciiPixel
import app.models.{Image, PixelGrid}
import converters.imageToText.AsciiImageToTextConverter
import org.scalatest.FunSuite

class AsciiImageToTextConverterTest extends FunSuite {

  private val converter = new AsciiImageToTextConverter

  test("ascii to text converter test multiple rows") {
    val image = Image(PixelGrid(Seq(
      Seq(AsciiPixel('A'), AsciiPixel('B')),
      Seq(AsciiPixel('#'), AsciiPixel('$')),
      Seq(AsciiPixel('!'), AsciiPixel('@'))
    )))
    assert("AB\n#$\n!@\n" == converter.convert(image))
  }

  test("ascii to text converter test one row") {
    val image = Image(PixelGrid(Seq(
      Seq(AsciiPixel('!'), AsciiPixel('@'), AsciiPixel('#'))
    )))
    assert("!@#\n" == converter.convert(image))
  }

}

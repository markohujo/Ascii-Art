package converters

import app.models.pixel.AsciiPixel
import app.models.{Image, PixelGrid}
import converters.imageToText.AsciiImageToTextConverter
import org.scalatest.FunSuite

class AsciiImageToTextConverterTest extends FunSuite {

  private val image = Image(PixelGrid(Seq(
    Seq(AsciiPixel('A'), AsciiPixel('B')),
    Seq(AsciiPixel('#'), AsciiPixel('$')),
    Seq(AsciiPixel('!'), AsciiPixel('@'))
  )))

  private val converter = new AsciiImageToTextConverter

  test("converter test") {
    val resultText = converter.convert(image)
    println(resultText)
    assert("AB\n#$\n!@\n" == resultText)
  }


}

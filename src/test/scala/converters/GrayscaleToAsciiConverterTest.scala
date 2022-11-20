package converters

import app.models.pixel.{AsciiPixel, GrayscalePixel}
import app.models.{Image, PixelGrid}
import converters.pixel.GrayscaleToAsciiConverter
import org.scalatest.FunSuite

class GrayscaleToAsciiConverterTest extends FunSuite {

  private val image = Image(PixelGrid(Seq(
    Seq(GrayscalePixel(255), GrayscalePixel(151), GrayscalePixel(100)),
    Seq(GrayscalePixel(202), GrayscalePixel(0), GrayscalePixel(251))
  )))

  private val converter = new GrayscaleToAsciiConverter

  test("converter test") {
    val convertedImage = image.transform(converter.convert)
    for (i <- 0 until convertedImage.height) {
      for (j <- 0 until convertedImage.width) {
        assert(correctAsciiValue(image.pixelAt(i, j).value) == convertedImage.pixelAt(i, j))
      }
    }
  }

  private def correctAsciiValue(value: Int): AsciiPixel = {
    val chars = "$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\\|()1{}[]?-_+~<>i!lI;:,\"^`'. "
    AsciiPixel(chars.charAt((chars.length - 1) * value / 255))
  }

}

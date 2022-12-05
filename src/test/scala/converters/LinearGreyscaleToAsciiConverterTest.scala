package converters

import app.models.pixel.{AsciiPixel, GreyscalePixel}
import app.models.{Image, PixelGrid}
import converters.pixel.LinearGreyscaleToAsciiConverter
import org.scalatest.FunSuite
import utils.Constants

class LinearGreyscaleToAsciiConverterTest extends FunSuite {

  private val image = Image(PixelGrid(Seq(
    Seq(GreyscalePixel(255), GreyscalePixel(151), GreyscalePixel(100)),
    Seq(GreyscalePixel(202), GreyscalePixel(0), GreyscalePixel(251))
  )))

  private val converter = new LinearGreyscaleToAsciiConverter

  test("grayscale to ascii converter test") {
    val convertedImage = image.transform(converter.convert)
    for (i <- 0 until convertedImage.height) {
      for (j <- 0 until convertedImage.width) {
        assert(correctAsciiValue(image.pixelAt(i, j).value) == convertedImage.pixelAt(i, j))
      }
    }
  }

  private def correctAsciiValue(value: Int): AsciiPixel = {
    AsciiPixel(Constants.DefaultTransformationTable.charAt((Constants.DefaultTransformationTable.length - 1) * value / 255))
  }

}

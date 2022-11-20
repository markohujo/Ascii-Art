package converters

import app.models.pixel.{GrayscalePixel, RGBPixel}
import app.models.{Image, PixelGrid}
import converters.pixel.RGBToGrayscaleConverter
import org.scalatest.FunSuite

import java.awt.Color

class RGBToGrayscaleConverterTest extends FunSuite {

  private val image = Image(PixelGrid(Seq(
    Seq(RGBPixel(Color.BLACK), RGBPixel(Color.BLACK)),
    Seq(RGBPixel(Color.CYAN), RGBPixel(Color.CYAN)),
    Seq(RGBPixel(Color.WHITE), RGBPixel(Color.WHITE))
  )))

  private val converter = new RGBToGrayscaleConverter

  test("test converter") {
    val convertedImage = image.transform(converter.convert)
    for (i <- 0 until convertedImage.height) {
      for (j <- 0 until convertedImage.width) {
        val pixel = image.pixelAt(i, j)
        assert(correctGrayscaleValue(pixel.red, pixel.green, pixel.green) == convertedImage.pixelAt(i, j))
      }
    }
  }

  private def correctGrayscaleValue(red: Int, green: Int, blue: Int): GrayscalePixel = GrayscalePixel((red * 0.3 + green * 0.59 + blue * 0.11).toInt)
}

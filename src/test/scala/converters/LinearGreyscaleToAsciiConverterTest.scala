package converters

import app.models.pixel.AsciiPixel
import converters.pixel.LinearGreyscaleToAsciiConverter
import utils.Constants

class LinearGreyscaleToAsciiConverterTest extends GreyscaleToAsciiConverterTest[LinearGreyscaleToAsciiConverter] {

  converter = new LinearGreyscaleToAsciiConverter

  override def correctAsciiValue(value: Int): AsciiPixel = {
    AsciiPixel(Constants.DefaultTransformationTable.charAt((Constants.DefaultTransformationTable.length - 1) * value / 255))
  }
}
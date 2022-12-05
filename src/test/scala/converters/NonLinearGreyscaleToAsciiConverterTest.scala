package converters

import app.models.pixel.AsciiPixel
import converters.pixel.NonLinearGreyscaleToAsciiConverter

class NonLinearGreyscaleToAsciiConverterTest extends GreyscaleToAsciiConverterTest[NonLinearGreyscaleToAsciiConverter] {

  converter = new NonLinearGreyscaleToAsciiConverter

  override protected def correctAsciiValue(value: Int): AsciiPixel = {
    AsciiPixel('A') // TODO
  }
}

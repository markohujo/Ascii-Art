package converters

import app.models.pixel.AsciiPixel
import converters.pixel.NonLinearGreyscaleToAsciiConverter
import utils.Constants

class NonLinearGreyscaleToAsciiConverterTest extends GreyscaleToAsciiConverterTest[NonLinearGreyscaleToAsciiConverter] {

  converter = new NonLinearGreyscaleToAsciiConverter

  override protected def correctAsciiValue(value: Int): AsciiPixel = {
    if (value < 55)
      return AsciiPixel(Constants.DefaultNonLinearTransformationTable.head)
    if (value < 100)
      return AsciiPixel(Constants.DefaultNonLinearTransformationTable.charAt(1))
    if (value > 200)
      return AsciiPixel(Constants.DefaultNonLinearTransformationTable.last)
    if (value > 155)
      return AsciiPixel(Constants.DefaultNonLinearTransformationTable.charAt(Constants.DefaultNonLinearTransformationTable.length - 2))
    val index = (Constants.DefaultNonLinearTransformationTable.length - 1) * value / 155
    AsciiPixel(Constants.DefaultNonLinearTransformationTable.charAt(index))  }
}

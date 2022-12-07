package converters.pixel

import app.models.pixel.{AsciiPixel, GreyscalePixel}
import utils.Constants

class NonLinearGreyscaleToAsciiConverter extends GreyscaleToAsciiConverter {

  /**
   * Converts greyscale pixel to ascii pixel
   *
   * @param item greyscale pixel to convert
   * @return converted ascii pixel
   */
  override def convert(item: GreyscalePixel): AsciiPixel = {
    if (item.value < 50)
      return AsciiPixel(Constants.DefaultNonLinearTransformationTable.head)
    if (item.value < 100)
      return AsciiPixel(Constants.DefaultNonLinearTransformationTable.charAt(1))
    if (item.value > 200)
      return AsciiPixel(Constants.DefaultNonLinearTransformationTable.last)
    val index = (Constants.DefaultNonLinearTransformationTable.length - 1) * item.value / 200
    AsciiPixel(Constants.DefaultNonLinearTransformationTable.charAt(index))
  }
}

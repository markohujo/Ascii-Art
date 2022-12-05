package converters.pixel
import app.models.pixel.{AsciiPixel, GreyscalePixel}
import utils.Constants

/**
 *
 * @param transformationTable characters used as non-linear transformation table
 */
class NonLinearGreyscaleToAsciiConverter(transformationTable: String = Constants.DefaultNonLinearTransformationTable)
    extends GreyscaleToAsciiConverter(transformationTable) {

  /**
   * Converts greyscale pixel to ascii pixel
   *
   * @param item greyscale pixel to convert
   * @return converted ascii pixel
   */
  override def convert(item: GreyscalePixel): AsciiPixel = {
    if (item.value < 55)
      return AsciiPixel(transformationTable.head)
    if (item.value < 100)
      return AsciiPixel(transformationTable.charAt(1))
    if (item.value > 200)
      return AsciiPixel(transformationTable.last)
    if (item.value > 155)
      return AsciiPixel(transformationTable.charAt(transformationTable.length - 2))
    val index = (transformationTable.length - 1) * item.value / 155
    AsciiPixel(transformationTable.charAt(index))
  }
}

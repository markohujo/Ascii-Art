package converters.pixel

import app.models.pixel.{AsciiPixel, GreyscalePixel}
import utils.Constants

/**
 *
 * @param transformationTable characters used as linear transformation table
 */
class LinearGreyscaleToAsciiConverter(transformationTable: String = Constants.DefaultTransformationTable)
    extends GreyscaleToAsciiConverter {

  if (transformationTable.isEmpty || transformationTable.isBlank)
    throw new IllegalArgumentException("Empty transformation table")

  /**
   * Converts greyscale pixel to ascii pixel
   *
   * @param item greyscale pixel to convert
   * @return converted ascii pixel
   */
  override def convert(item: GreyscalePixel): AsciiPixel = {
    val index = (transformationTable.length - 1) * item.value / 255
    AsciiPixel(transformationTable.charAt(index))
  }
}

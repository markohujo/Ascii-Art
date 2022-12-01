package converters.pixel

import app.models.pixel.{AsciiPixel, GrayscalePixel}
import utils.Constants

/**
 *
 * @param transformationTable characters used as linear transformation table
 */
class LinearGrayscaleToAsciiConverter(transformationTable: String = Constants.DefaultTransformationTable)
    extends GrayscaleToAsciiConverter(transformationTable) {

  /**
   * Converts greyscale pixel to ascii pixel
   *
   * @param item greyscale pixel to convert
   * @return converted ascii pixel
   */
  override def convert(item: GrayscalePixel): AsciiPixel = {
    val index = (transformationTable.length - 1) * item.value / 255
    AsciiPixel(transformationTable.charAt(index))
  }
}

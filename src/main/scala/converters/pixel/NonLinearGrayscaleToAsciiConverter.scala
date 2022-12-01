package converters.pixel
import app.models.pixel.{AsciiPixel, GrayscalePixel}
import utils.Constants

/**
 *
 * @param transformationTable characters used as non-linear transformation table
 */
class NonLinearGrayscaleToAsciiConverter(transformationTable: String = Constants.DefaultNonLinearTransformationTable)
    extends GrayscaleToAsciiConverter(transformationTable) {

  /**
   * Converts greyscale pixel to ascii pixel
   *
   * @param item greyscale pixel to convert
   * @return converted ascii pixel
   */
  override def convert(item: GrayscalePixel): AsciiPixel =
    ???
}

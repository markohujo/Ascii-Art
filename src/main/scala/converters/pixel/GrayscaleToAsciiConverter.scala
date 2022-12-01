package converters.pixel

import app.models.pixel.{AsciiPixel, GrayscalePixel}

/**
 *
 * @param transformationTable characters used as transformation table
 */
abstract class GrayscaleToAsciiConverter(transformationTable: String)
    extends PixelConverter[GrayscalePixel, AsciiPixel] {}

package converters.pixel

import app.models.pixel.{AsciiPixel, GreyscalePixel}

/**
 *
 * @param transformationTable characters used as transformation table
 */
abstract class GreyscaleToAsciiConverter(transformationTable: String)
    extends PixelConverter[GreyscalePixel, AsciiPixel] {}

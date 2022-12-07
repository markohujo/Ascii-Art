package converters.pixel

import app.models.pixel.{AsciiPixel, GreyscalePixel}

trait GreyscaleToAsciiConverter extends PixelConverter[GreyscalePixel, AsciiPixel] {}

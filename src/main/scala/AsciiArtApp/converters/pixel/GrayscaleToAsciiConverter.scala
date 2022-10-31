package AsciiArtApp.converters.pixel

import AsciiArtApp.models.pixel.{AsciiPixel, GrayscalePixel}

class GrayscaleToAsciiConverter extends PixelConverter[GrayscalePixel, AsciiPixel] {
  override def convert(from: GrayscalePixel): AsciiPixel = {
    ???
  }
}

package AsciiArtApp.converters.pixel

import AsciiArtApp.models.pixel.{GrayscalePixel, RGBPixel}

class RGBToGrayscaleConverter extends PixelConverter[RGBPixel, GrayscalePixel] {
  override def convert(from: RGBPixel): GrayscalePixel = {
    new GrayscalePixel((0.3 * from.red) + (0.59 * from.green) + (0.11 * from.blue))
  }
}

package converters.pixel

import app.models.pixel.{GrayscalePixel, RGBPixel}

class RGBToGrayscaleConverter extends PixelConverter[RGBPixel, GrayscalePixel] {
  override def convert(item: RGBPixel): GrayscalePixel = {
    GrayscalePixel(((0.3 * item.red) + (0.59 * item.green) + (0.11 * item.blue)).toInt)
  }
}

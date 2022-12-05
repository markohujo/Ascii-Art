package converters.pixel

import app.models.pixel.{GreyscalePixel, RGBPixel}

class RGBToGrayscaleConverter extends PixelConverter[RGBPixel, GreyscalePixel] {
  override def convert(item: RGBPixel): GreyscalePixel = {
    GreyscalePixel(((0.3 * item.red) + (0.59 * item.green) + (0.11 * item.blue)).toInt)
  }
}

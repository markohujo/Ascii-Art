package converters.pixel

import app.models.pixel.{GreyscalePixel, RgbPixel}

class RgbToGrayscaleConverter extends PixelConverter[RgbPixel, GreyscalePixel] {
  override def convert(item: RgbPixel): GreyscalePixel = {
    GreyscalePixel(((0.3 * item.red) + (0.59 * item.green) + (0.11 * item.blue)).toInt)
  }
}

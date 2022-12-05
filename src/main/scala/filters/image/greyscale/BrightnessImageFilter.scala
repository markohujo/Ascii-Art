package filters.image.greyscale

import app.models.Image
import app.models.pixel.GreyscalePixel
import utils.correctors.PixelValueCorrector

class BrightnessImageFilter(value: Int) extends GreyscaleImageFilter {
  override def apply(item: Image[GreyscalePixel]): Image[GreyscalePixel] = {
    item.transform(pixel => GreyscalePixel(PixelValueCorrector.corrected(pixel.value + value)))
  }
}

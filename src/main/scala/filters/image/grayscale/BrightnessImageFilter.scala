package filters.image.grayscale

import app.models.Image
import app.models.pixel.GrayscalePixel
import utils.correctors.PixelValueCorrector

class BrightnessImageFilter(value: Int) extends GrayscaleImageFilter {
  override def apply(item: Image[GrayscalePixel]): Image[GrayscalePixel] = {
    item.transform(pixel => GrayscalePixel(PixelValueCorrector.corrected(pixel.value + value)))
  }
}

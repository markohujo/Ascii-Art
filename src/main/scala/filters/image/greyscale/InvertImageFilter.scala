package filters.image.greyscale

import app.models.Image
import app.models.pixel.GreyscalePixel

class InvertImageFilter extends GreyscaleImageFilter {
  override def apply(item: Image[GreyscalePixel]): Image[GreyscalePixel] = {
    item.transform(pixel => GreyscalePixel(255 - pixel.value))
  }
}

package app.filters.image.grayscale
import app.models.Image
import app.models.pixel.GrayscalePixel

class InvertImageFilter extends GrayscaleImageFilter {
  override def filter(item: Image[GrayscalePixel]): Image[GrayscalePixel] = {
    item.transform(pixel => GrayscalePixel(255 - pixel.value))
  }
}

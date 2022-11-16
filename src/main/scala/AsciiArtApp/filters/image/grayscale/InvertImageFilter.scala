package AsciiArtApp.filters.image.grayscale
import AsciiArtApp.models.Image
import AsciiArtApp.models.pixel.GrayscalePixel

class InvertImageFilter extends GrayscaleImageFilter {
  override def filter(item: Image[GrayscalePixel]): Image[GrayscalePixel] = {
    item.transform(pixel => GrayscalePixel(255 - pixel.value))
  }
}

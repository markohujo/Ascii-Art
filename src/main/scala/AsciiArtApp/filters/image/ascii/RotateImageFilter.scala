package AsciiArtApp.filters.image.ascii
import AsciiArtApp.models.Image
import AsciiArtApp.models.pixel.AsciiPixel

class RotateImageFilter(value: Int) extends AsciiImageFilter {
  override def filter(item: Image[AsciiPixel]): Image[AsciiPixel] = {
    item
  }
}

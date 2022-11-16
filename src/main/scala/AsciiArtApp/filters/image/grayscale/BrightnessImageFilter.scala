package AsciiArtApp.filters.image.grayscale
import AsciiArtApp.models.Image
import AsciiArtApp.models.pixel.GrayscalePixel

class BrightnessImageFilter(value: Int) extends GrayscaleImageFilter {
  override def filter(item: Image[GrayscalePixel]): Image[GrayscalePixel] = {
    item.transform(pixel => {
      var updatedValue = pixel.value + value
      if (updatedValue > 255)
        updatedValue = 255
      else if (updatedValue < 0)
        updatedValue = 0
      GrayscalePixel(updatedValue)
    })
  }
}

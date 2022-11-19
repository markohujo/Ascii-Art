package converters.pixel

import app.models.pixel.{AsciiPixel, GrayscalePixel}

class GrayscaleToAsciiConverter extends PixelConverter[GrayscalePixel, AsciiPixel] {

  private val chars = "$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\\|()1{}[]?-_+~<>i!lI;:,\"^`'. "

  override def convert(item: GrayscalePixel): AsciiPixel = {
    val index = (chars.length - 1) * item.value / 255
    AsciiPixel(chars.charAt(index))
  }
}

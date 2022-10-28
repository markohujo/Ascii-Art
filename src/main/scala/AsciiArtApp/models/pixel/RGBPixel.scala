package AsciiArtApp.models.pixel

import java.awt.Color

class RGBPixel(id: Long, value: Color) extends Pixel {
  override def getId: Long = id
}

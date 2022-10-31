package AsciiArtApp.models.pixel

import java.awt.Color

class RGBPixel(value: Color) extends Pixel {
  val red: Int = value.getRed
  val green: Int = value.getGreen
  val blue: Int = value.getBlue
}

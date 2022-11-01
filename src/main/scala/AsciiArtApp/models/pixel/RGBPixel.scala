package AsciiArtApp.models.pixel

import java.awt.Color

class RGBPixel(value: Color) extends Pixel {
  def red: Int = value.getRed

  def green: Int = value.getGreen

  def blue: Int = value.getBlue
}

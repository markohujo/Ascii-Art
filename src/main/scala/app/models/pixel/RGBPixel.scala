package app.models.pixel

import java.awt.Color

case class RGBPixel(value: Color) extends Pixel {
  def red: Int = value.getRed

  def green: Int = value.getGreen

  def blue: Int = value.getBlue
}

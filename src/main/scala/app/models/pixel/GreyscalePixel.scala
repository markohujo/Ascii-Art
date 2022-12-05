package app.models.pixel

case class GreyscalePixel(value: Int) extends Pixel {
  if (value < 0 || value > 255)
    throw new IllegalArgumentException("Value must be between 0 and 255.")
}

package AsciiArtApp.models

import AsciiArtApp.models.pixel.Pixel

class Image[T <: Pixel](pixelGrid: PixelGrid[T]) {

  def height: Int = pixelGrid.height

  def width: Int = pixelGrid.width
}

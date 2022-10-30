package AsciiArtApp.models

import AsciiArtApp.models.pixel.Pixel

class Image[T <: Pixel](id: Long, pixelGrid: PixelGrid[T]) extends Entity[Long] {
  override def id: Long = id

  def height: Int = pixelGrid.height

  def width: Int = pixelGrid.width
}

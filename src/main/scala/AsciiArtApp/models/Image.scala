package AsciiArtApp.models

import AsciiArtApp.models.pixel.Pixel

class Image[T <: Pixel](id: Long, pixels: Seq[Seq[T]]) extends Entity[Long] {
  override def getId: Long = id
}

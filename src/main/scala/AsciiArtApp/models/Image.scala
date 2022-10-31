package AsciiArtApp.models

import AsciiArtApp.models.pixel.Pixel

class Image[T <: Pixel](pixelGrid: PixelGrid[T]) {
  val height: Int = pixelGrid.height
  val width: Int = pixelGrid.width

  def transform[S <: Pixel](transformer: T => S): Image[S] = {
    new Image[S](pixelGrid.transform(transformer))
  }

  def pixelAt(x: Int, y: Int): T = pixelGrid.at(x, y)
}

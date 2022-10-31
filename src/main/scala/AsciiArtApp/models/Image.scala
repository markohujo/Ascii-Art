package AsciiArtApp.models

import AsciiArtApp.models.pixel.Pixel

class Image[T <: Pixel](pixelGrid: PixelGrid[T]) {

  def height: Int = pixelGrid.height

  def width: Int = pixelGrid.width

  def transform[S <: Pixel](transformer: T => S): Image[S] = {
    new Image[S](pixelGrid.transform(transformer))
  }
}

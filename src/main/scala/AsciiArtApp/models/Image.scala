package AsciiArtApp.models

import AsciiArtApp.models.pixel.Pixel

class Image[T <: Pixel](pixelGrid: PixelGrid[T]) {
  def height: Int = pixelGrid.height

  def width: Int = pixelGrid.width

  /**
   * Applies the given function to each pixel of this image
   *
   * @param transformer function to be applied
   * @tparam S subclass of Pixel that the new image will consist of
   * @return Transformed image
   */
  def transform[S <: Pixel](transformer: T => S): Image[S] = {
    new Image[S](pixelGrid.transform(transformer))
  }

  /**
   * @param x row index
   * @param y column index
   * @return Pixel at the given position
   */
  def pixelAt(x: Int, y: Int): T = pixelGrid.at(x, y)
}

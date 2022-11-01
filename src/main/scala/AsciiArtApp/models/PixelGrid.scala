package AsciiArtApp.models

import AsciiArtApp.models.pixel.Pixel

class PixelGrid[T <: Pixel](pixels: Seq[Seq[T]]) {
  if (pixels.isEmpty)
    throw new IllegalArgumentException("")

  if (pixels.exists(e => e.isEmpty))
    throw new IllegalArgumentException("")

  def height: Int = pixels.length

  def width: Int = pixels.head.length

  if (pixels.exists(e => e.length != width))
    throw new IllegalArgumentException("")

  /**
   * Applies the given function to each pixel of this pixel grid
   *
   * @param transformer function to be applied
   * @tparam S subclass of Pixel that the new pixel grid will consist of
   * @return Pixel grid consisting of transformed pixels
   */
  def transform[S <: Pixel](transformer: T => S): PixelGrid[S] = {
    var newPixels: Seq[Seq[S]] = Seq.empty[Seq[S]]

    for (row <- pixels) {
      var newRow: Seq[S] = Seq.empty[S]
      for (pixel <- row)
        newRow = newRow.appended(transformer(pixel))
      newPixels = newPixels.appended(newRow)
    }

    new PixelGrid[S](newPixels)
  }

  /**
   * @param x row index
   * @param y column index
   * @throws IndexOutOfBoundsException if any of the given indices is out of bounds
   * @return Pixel at the given position
   */
  def at(x: Int, y: Int): T = {
    if (x < 0 || x >= height)
      throw new IndexOutOfBoundsException("Row index out of bounds.")

    if (y < 0 || y >= width)
      throw new IndexOutOfBoundsException("Column index out of bounds")

    pixels(x)(y)
  }
}

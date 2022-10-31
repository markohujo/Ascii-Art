package AsciiArtApp.models

import AsciiArtApp.models.pixel.Pixel

class PixelGrid[T <: Pixel](pixels: Seq[Seq[T]]) {
  if (pixels.isEmpty)
    throw new IllegalArgumentException("")

  if (pixels.exists(e => e.isEmpty))
    throw new IllegalArgumentException("")

  val height: Int = pixels.length
  val width: Int = pixels.head.length

  if (pixels.exists(e => e.length != width))
    throw new IllegalArgumentException("")

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

  def at(x: Int, y: Int): T = pixels(x)(y)
}

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
    for (pixelRow <- pixels; pixel <- pixelRow) {
      var newPixelRow: Seq[S] = Seq.empty[S]
      newPixelRow = newPixelRow.appended(transformer(pixel))
      newPixels = newPixels.appended(newPixelRow)
    }
    new PixelGrid[S](newPixels)
  }
}

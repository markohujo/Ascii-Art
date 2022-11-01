package AsciiArtApp.converters.toText

import AsciiArtApp.models.Image
import AsciiArtApp.models.pixel.AsciiPixel

import scala.collection.mutable

class AsciiToStringConverter extends ToTextConverter[Image[AsciiPixel]] {
  override def convert(from: Image[AsciiPixel]): String = {
    val builder = new mutable.StringBuilder
    for (rowIndex <- 0 until from.height) {
      for (columnIndex <- 0 until from.width)
        builder.addOne(from.pixelAt(from.height - 1 - rowIndex, columnIndex).getValue)
      builder.addOne('\n')
    }
    builder.toString
  }
}

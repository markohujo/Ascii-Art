package AsciiArtApp.converters.toText

import AsciiArtApp.models.Image
import AsciiArtApp.models.pixel.AsciiPixel

import scala.collection.mutable

class AsciiToStringConverter extends ToTextConverter[Image[AsciiPixel]] {
  override def convert(item: Image[AsciiPixel]): String = {
    val builder = new mutable.StringBuilder
    for (rowIndex <- 0 until item.height) {
      for (columnIndex <- 0 until item.width)
        builder.addOne(item.pixelAt(item.height - 1 - rowIndex, columnIndex).value)
      builder.addOne('\n')
    }
    builder.toString
  }
}

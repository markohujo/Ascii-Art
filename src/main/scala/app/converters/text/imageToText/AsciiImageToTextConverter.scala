package app.converters.text.imageToText

import app.models.Image
import app.models.pixel.AsciiPixel

import scala.collection.mutable

class AsciiImageToTextConverter extends ImageToTextConverter[AsciiPixel] {
  private val builder = new mutable.StringBuilder

  override def convert(item: Image[AsciiPixel]): String = {
    for (rowIndex <- 0 until item.height)
      convertRow(rowIndex, item)
    builder.toString
  }

  private def convertRow(rowIndex: Int, item: Image[AsciiPixel]): Unit = {
    for (columnIndex <- 0 until item.width)
      builder.addOne(item.pixelAt(item.height - 1 - rowIndex, columnIndex).value)
    builder.addOne('\n')
  }
}

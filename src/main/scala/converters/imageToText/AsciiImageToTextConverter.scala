package converters.imageToText

import app.models.Image
import app.models.pixel.AsciiPixel

import scala.collection.mutable

class AsciiImageToTextConverter extends ImageToTextConverter[AsciiPixel] {
  private val builder = new mutable.StringBuilder

  override def convert(item: Image[AsciiPixel]): String = {
    builder.clear()
    for (rowIndex <- 0 until item.height)
      convertRow(rowIndex, item)
    builder.toString
  }

  private def convertRow(rowIndex: Int, image: Image[AsciiPixel]): Unit = {
    for (columnIndex <- 0 until image.width)
      builder.addOne(image.pixelAt(rowIndex, columnIndex).value)
    builder.addOne('\n')
  }
}

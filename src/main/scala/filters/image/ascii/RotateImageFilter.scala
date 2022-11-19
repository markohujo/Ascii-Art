package filters.image.ascii

import app.models.pixel.AsciiPixel
import app.models.{Image, PixelGrid}

import scala.collection.mutable.ArrayBuffer

class RotateImageFilter(value: Int) extends AsciiImageFilter {

  if (value % 90 != 0)
    throw new IllegalArgumentException("Unsupported rotation value. Only rotations dividable by 90 degrees are available.")

  override def apply(item: Image[AsciiPixel]): Image[AsciiPixel] = {
    value % 360 match {
      case 0 => item
      case 90 | -270 => rotate(item)
      case 180 | -180 => rotate(rotate(item))
      case 270 | -90 => rotate(rotate(rotate(item)))
    }
  }

  private def rotate(item: Image[AsciiPixel]): Image[AsciiPixel] = {
    val result = new ArrayBuffer[ArrayBuffer[AsciiPixel]](item.width)
    for (_ <- 0 until item.width) {
      val row = new ArrayBuffer[AsciiPixel](item.height)
      for (_ <- 0 until item.height)
        row.addOne(null)
      result.addOne(row)
    }

    for (rowIndex <- 0 until item.height) {
      for (columnIndex <- 0 until item.width) {
        result(columnIndex)(item.height - rowIndex - 1) = item.pixelAt(rowIndex, columnIndex)
      }
    }

    new Image[AsciiPixel](new PixelGrid[AsciiPixel](result.map(r => r.toSeq).toSeq))
  }
}
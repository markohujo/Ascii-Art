package app.models

import app.models.pixel.RgbPixel
import org.scalatest.FunSuite

import java.awt.Color

class PixelGridTest extends FunSuite {

  val pixelGrid: PixelGrid[RgbPixel] = createValidPixelGrid()

  test("empty pixels") {
    val pixels = Seq.empty[Seq[RgbPixel]]
    assertThrows[IllegalArgumentException] {
      new PixelGrid[RgbPixel](pixels)
    }
  }

  test("invalid pixels - empty row") {
    val pixels = Seq(
      Seq(RgbPixel(Color.BLACK), RgbPixel(Color.GREEN)),
      Seq.empty,
      Seq(RgbPixel(Color.BLACK), RgbPixel(Color.GREEN))
    )
    assertThrows[IllegalArgumentException] {
      new PixelGrid[RgbPixel](pixels)
    }
  }

  test("invalid pixels - invalid size") {
    val pixels = Seq(
      Seq(RgbPixel(Color.BLACK), RgbPixel(Color.GREEN), RgbPixel(Color.GREEN)),
      Seq(RgbPixel(Color.BLACK), RgbPixel(Color.GREEN)),
      Seq(RgbPixel(Color.BLACK), RgbPixel(Color.GREEN))
    )
    assertThrows[IllegalArgumentException] {
      new PixelGrid[RgbPixel](pixels)
    }
  }

  test("test height and width") {
    assert(pixelGrid.height == 3)
    assert(pixelGrid.width == 2)
  }

  test("test transform") {
    assert(pixelGrid.height == 3)
    assert(pixelGrid.width == 2)

    val transformedPixelGrid = pixelGrid.transform(_ => RgbPixel(Color.WHITE))
    assert(transformedPixelGrid.height == 3)
    assert(transformedPixelGrid.width == 2)

    for (rowIndex <- 0 until transformedPixelGrid.height) {
      for (columnIndex <- 0 until transformedPixelGrid.width) {
        assert(transformedPixelGrid.at(rowIndex, columnIndex).red == 255)
        assert(transformedPixelGrid.at(rowIndex, columnIndex).green == 255)
        assert(transformedPixelGrid.at(rowIndex, columnIndex).blue == 255)
      }
    }
  }

  test("test at - invalid indices") {
    assert(pixelGrid.height == 3)
    assert(pixelGrid.width == 2)
    assertThrows[IndexOutOfBoundsException] {
      pixelGrid.at(-1, 0)
    }
    assertThrows[IndexOutOfBoundsException] {
      pixelGrid.at(0, -1)
    }
    assertThrows[IndexOutOfBoundsException] {
      pixelGrid.at(4, 0)
    }
    assertThrows[IndexOutOfBoundsException] {
      pixelGrid.at(0, 3)
    }
    assertThrows[IndexOutOfBoundsException] {
      pixelGrid.at(100, 100)
    }
    assertThrows[IndexOutOfBoundsException] {
      pixelGrid.at(-100, -100)
    }
  }

  test("test at") {
    assert(pixelGrid.at(0, 0).equals(RgbPixel(Color.BLACK)))
    assert(pixelGrid.at(1, 1).equals(RgbPixel(Color.GREEN)))
  }

  private def createValidPixelGrid(): PixelGrid[RgbPixel] = {
    val pixels = Seq(
      Seq(RgbPixel(Color.BLACK), RgbPixel(Color.GREEN)),
      Seq(RgbPixel(Color.BLACK), RgbPixel(Color.GREEN)),
      Seq(RgbPixel(Color.BLACK), RgbPixel(Color.GREEN))
    )
    new PixelGrid[RgbPixel](pixels)
  }
}

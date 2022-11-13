package AsciiArtApp.models

import AsciiArtApp.models.pixel.RGBPixel
import org.scalatest.FunSuite

import java.awt.Color

class PixelGridTest extends FunSuite {

  val pixelGrid: PixelGrid[RGBPixel] = createValidPixelGrid()

  test("empty pixels") {
    val pixels = Seq.empty[Seq[RGBPixel]]
    assertThrows[IllegalArgumentException] {
      new PixelGrid[RGBPixel](pixels)
    }
  }

  test("invalid pixels - empty row") {
    val pixels = Seq(
      Seq(RGBPixel(Color.BLACK), RGBPixel(Color.GREEN)),
      Seq.empty,
      Seq(RGBPixel(Color.BLACK), RGBPixel(Color.GREEN))
    )
    assertThrows[IllegalArgumentException] {
      new PixelGrid[RGBPixel](pixels)
    }
  }

  test("invalid pixels - invalid size") {
    val pixels = Seq(
      Seq(RGBPixel(Color.BLACK), RGBPixel(Color.GREEN), new RGBPixel(Color.GREEN)),
      Seq(RGBPixel(Color.BLACK), RGBPixel(Color.GREEN)),
      Seq(RGBPixel(Color.BLACK), RGBPixel(Color.GREEN))
    )
    assertThrows[IllegalArgumentException] {
      new PixelGrid[RGBPixel](pixels)
    }
  }

  test("test height and width") {
    assert(pixelGrid.height == 3)
    assert(pixelGrid.width == 2)
  }

  test("test transform") {
    assert(pixelGrid.height == 3)
    assert(pixelGrid.width == 2)

    val transformedPixelGrid = pixelGrid.transform(e => new RGBPixel(Color.WHITE))
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
    assert(pixelGrid.at(0, 0).equals(RGBPixel(Color.BLACK)))
    assert(pixelGrid.at(1, 1).equals(RGBPixel(Color.GREEN)))
  }

  private def createValidPixelGrid(): PixelGrid[RGBPixel] = {
    val pixels = Seq(
      Seq(RGBPixel(Color.BLACK), RGBPixel(Color.GREEN)),
      Seq(RGBPixel(Color.BLACK), RGBPixel(Color.GREEN)),
      Seq(RGBPixel(Color.BLACK), RGBPixel(Color.GREEN))
    )
    new PixelGrid[RGBPixel](pixels)
  }
}

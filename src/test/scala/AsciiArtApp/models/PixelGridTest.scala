package AsciiArtApp.models

import AsciiArtApp.models.pixel.{Pixel, RGBPixel}
import org.scalatest.FunSuite

import java.awt.Color

class PixelGridTest extends FunSuite {

  test("empty pixels") {
    val pixels = Seq.empty[Seq[RGBPixel]]
    assertThrows[IllegalArgumentException] {
      new PixelGrid[RGBPixel](pixels)
    }
  }

  test("invalid pixels - empty row") {
    val pixels = Seq(
      Seq(new RGBPixel(Color.BLACK), new RGBPixel(Color.GREEN)),
      Seq.empty,
      Seq(new RGBPixel(Color.BLACK), new RGBPixel(Color.GREEN))
    )
    assertThrows[IllegalArgumentException] {
      new PixelGrid[RGBPixel](pixels)
    }
  }

  test("invalid pixels - invalid size") {
    val pixels = Seq(
      Seq(new RGBPixel(Color.BLACK), new RGBPixel(Color.GREEN), new RGBPixel(Color.GREEN)),
      Seq(new RGBPixel(Color.BLACK), new RGBPixel(Color.GREEN)),
      Seq(new RGBPixel(Color.BLACK), new RGBPixel(Color.GREEN))
    )
    assertThrows[IllegalArgumentException] {
      new PixelGrid[RGBPixel](pixels)
    }
  }

  test("test height and width") {
    val pixelGrid = createValidPixelGrid()
    assert(pixelGrid.height == 3)
    assert(pixelGrid.width == 2)
  }

  test("test transform") {
    val pixelGrid = createValidPixelGrid()
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

  private def createValidPixelGrid(): PixelGrid[RGBPixel] = {
    val pixels = Seq(
      Seq(new RGBPixel(Color.BLACK), new RGBPixel(Color.GREEN)),
      Seq(new RGBPixel(Color.BLACK), new RGBPixel(Color.GREEN)),
      Seq(new RGBPixel(Color.BLACK), new RGBPixel(Color.GREEN))
    )
    new PixelGrid[RGBPixel](pixels)
  }

  // TODO add more

}

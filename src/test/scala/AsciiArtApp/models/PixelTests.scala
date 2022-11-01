package AsciiArtApp.models

import AsciiArtApp.models.pixel.RGBPixel
import org.scalatest.FunSuite

import java.awt.Color

class PixelTests extends FunSuite {

  test("correct rgb value test") {
    var pixel = new RGBPixel(Color.BLACK)
    assert(pixel.red == 0)
    assert(pixel.green == 0)
    assert(pixel.blue == 0)

    pixel = new RGBPixel(Color.WHITE)
    assert(pixel.red == 255)
    assert(pixel.green == 255)
    assert(pixel.blue == 255)
  }
}

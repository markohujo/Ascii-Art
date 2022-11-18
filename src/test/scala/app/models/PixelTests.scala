package app.models

import app.models.pixel.{GrayscalePixel, RGBPixel}
import org.scalatest.FunSuite

import java.awt.Color

class PixelTests extends FunSuite {

  test("invalid grayscale value test") {
    assertThrows[IllegalArgumentException] {
      GrayscalePixel(300)
    }
    assertThrows[IllegalArgumentException] {
      GrayscalePixel(-1)
    }
  }

  test("valid rgb value test") {
    var pixel = RGBPixel(Color.BLACK)
    assert(pixel.red == 0)
    assert(pixel.green == 0)
    assert(pixel.blue == 0)

    pixel = RGBPixel(Color.WHITE)
    assert(pixel.red == 255)
    assert(pixel.green == 255)
    assert(pixel.blue == 255)
  }

  test("valid grayscale value test") {
    val pixel = GrayscalePixel(255)
    assert(pixel.value == 255)
  }

  test("valid ascii value test") {
    val pixel = GrayscalePixel('#')
    assert(pixel.value == '#')
  }
}

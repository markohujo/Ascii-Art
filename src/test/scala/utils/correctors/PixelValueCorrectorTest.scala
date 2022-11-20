package utils.correctors

import org.scalatest.FunSuite

class PixelValueCorrectorTest extends FunSuite {
  test("test pixel value corrector") {
    assert(PixelValueCorrector.corrected(255) == 255)
    assert(PixelValueCorrector.corrected(0) == 0)
    assert(PixelValueCorrector.corrected(100) == 100)
    assert(PixelValueCorrector.corrected(200) == 200)
    assert(PixelValueCorrector.corrected(1) == 1)
    assert(PixelValueCorrector.corrected(254) == 254)
    assert(PixelValueCorrector.corrected(256) == 255)
    assert(PixelValueCorrector.corrected(-1) == 0)
    assert(PixelValueCorrector.corrected(-100) == 0)
    assert(PixelValueCorrector.corrected(300) == 255)
  }
}

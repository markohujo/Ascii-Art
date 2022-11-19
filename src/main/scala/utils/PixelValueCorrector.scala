package utils

import utils.correctors.Corrector

object PixelValueCorrector extends Corrector[Int] {
  /**
   * Checks if pixel value is in range between 0 and 255 (both inclusive)
   *
   * @param value - pixel to correct
   * @return corrected pixel
   */
  override def corrected(value: Int): Int = {
    if (value > 255)
      return 255
    if (value < 0)
      return 0
    value
  }
}

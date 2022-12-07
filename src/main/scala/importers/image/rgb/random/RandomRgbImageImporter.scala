package importers.image.rgb.random

import app.models.pixel.RgbPixel
import app.models.{Image, PixelGrid}
import importers.image.rgb.RgbImageImporter

import java.awt.Color
import scala.util.Random

class RandomRgbImageImporter extends RgbImageImporter {
  override def load: Image[RgbPixel] = {
    val random = new Random
    val height = random.between(100, 1001)
    val width = random.between(100, 1001)

    var pixels = Seq.empty[Seq[RgbPixel]]
    for (_ <- 0 until height) {
      var row = Seq.empty[RgbPixel]
      for (_ <- 0 until width) {
        val red = random.between(0, 256)
        val green = random.between(0, 256)
        val blue = random.between(0, 256)
        row = row.appended(RgbPixel(new Color(red, green, blue)))
      }
      pixels = pixels.appended(row)
    }

    new Image[RgbPixel](new PixelGrid[RgbPixel](pixels))
  }
}

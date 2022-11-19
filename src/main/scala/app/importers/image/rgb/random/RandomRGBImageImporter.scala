package app.importers.image.rgb.random

import app.importers.image.rgb.RGBImageImporter
import app.models.{Image, PixelGrid}
import app.models.pixel.RGBPixel

import java.awt.Color
import scala.util.Random

class RandomRGBImageImporter extends RGBImageImporter {
  override def load: Image[RGBPixel] = {
    val random = new Random
    val height = random.between(100, 1001)
    val width = random.between(100, 1001)

    var pixels = Seq.empty[Seq[RGBPixel]]
    for (_ <- 0 until height) {
      var row = Seq.empty[RGBPixel]
      for (_ <- 0 until width) {
        val red = random.between(0, 256)
        val green = random.between(0, 256)
        val blue = random.between(0, 256)
        row = row.appended(RGBPixel(new Color(red, green, blue)))
      }
      pixels = pixels.appended(row)
    }

    new Image[RGBPixel](new PixelGrid[RGBPixel](pixels))
  }
}

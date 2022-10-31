package AsciiArtApp.importers.image.rgb.input

import AsciiArtApp.importers.image.rgb.RGBImageImporter
import AsciiArtApp.models.pixel.RGBPixel
import AsciiArtApp.models.{Image, PixelGrid}

import java.awt.Color
import java.awt.image.BufferedImage

trait InputRGBImageImporter extends RGBImageImporter {

  def createImageFromBufferedImage(image: BufferedImage): Image[RGBPixel] = {
    var pixels: Seq[Seq[RGBPixel]] = Seq.empty[Seq[RGBPixel]]
    for (i <- 0 until image.getHeight) {
      var pixelRow: Seq[RGBPixel] = Seq.empty[RGBPixel]
      for (j <- 0 until image.getWidth)
        pixelRow = pixelRow.appended(new RGBPixel(new Color(image.getRGB(i, j))))
      pixels = pixels.appended(pixelRow)
    }

    new Image[RGBPixel](new PixelGrid[RGBPixel](pixels))
  }

}

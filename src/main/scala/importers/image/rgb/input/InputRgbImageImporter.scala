package importers.image.rgb.input

import app.models.pixel.RgbPixel
import app.models.{Image, PixelGrid}
import importers.image.rgb.RgbImageImporter

import java.awt.Color
import java.awt.image.BufferedImage

trait InputRgbImageImporter extends RgbImageImporter {

  /**
   * Constructs pixel grid and image consisting of RGB pixels from the given buffered image
   *
   * @param image buffered image
   * @return Created image
   */
  protected def createImageFromBufferedImage(image: BufferedImage): Image[RgbPixel] = {
    var pixels: Seq[Seq[RgbPixel]] = Seq.empty[Seq[RgbPixel]]

    for (i <- 0 until image.getHeight()) {
      var pixelRow: Seq[RgbPixel] = Seq.empty[RgbPixel]
      for (j <- 0 until image.getWidth())
        pixelRow = pixelRow.appended(RgbPixel(new Color(image.getRGB(j, i))))
      pixels = pixels.appended(pixelRow)
    }

    Image[RgbPixel](PixelGrid[RgbPixel](pixels))
  }
}

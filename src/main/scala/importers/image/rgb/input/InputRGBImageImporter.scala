package importers.image.rgb.input

import app.models.pixel.RGBPixel
import app.models.{Image, PixelGrid}
import importers.image.rgb.RGBImageImporter

import java.awt.Color
import java.awt.image.BufferedImage

trait InputRGBImageImporter extends RGBImageImporter {
  /**
   * Constructs pixel grid and image consisting of RGB pixels from the given buffered image
   *
   * @param image buffered image
   * @return Created image
   */
  protected def createImageFromBufferedImage(image: BufferedImage): Image[RGBPixel] = {
    var pixels: Seq[Seq[RGBPixel]] = Seq.empty[Seq[RGBPixel]]

    for (i <- 0 until image.getHeight()) {
      var pixelRow: Seq[RGBPixel] = Seq.empty[RGBPixel]
      for (j <- 0 until image.getWidth())
        pixelRow = pixelRow.appended(RGBPixel(new Color(image.getRGB(j, i))))
      pixels = pixels.appended(pixelRow)
    }

    new Image[RGBPixel](new PixelGrid[RGBPixel](pixels))
  }
}

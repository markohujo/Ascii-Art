package AsciiArtApp.importers.image.rgb.input

import AsciiArtApp.models.Image
import AsciiArtApp.models.pixel.RGBPixel

import java.io.File
import javax.imageio.ImageIO

class FileInputRGBImageImporter(path: String) extends InputRGBImageImporter {
  override def load: Image[RGBPixel] = {
    createImageFromBufferedImage(ImageIO.read(new File(path)))
  }
}

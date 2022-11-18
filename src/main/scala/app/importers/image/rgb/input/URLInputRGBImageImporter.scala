package app.importers.image.rgb.input
import app.models.Image
import app.models.pixel.RGBPixel

import java.net.URL
import javax.imageio.ImageIO

class URLInputRGBImageImporter(url: String) extends InputRGBImageImporter {
  override def load: Image[RGBPixel] = {
    createImageFromBufferedImage(ImageIO.read(new URL(url)))
  }
}

package importers.image.rgb.input

import app.models.Image
import app.models.pixel.RgbPixel

import java.net.URL
import javax.imageio.ImageIO

class UrlRgbImageImporter(url: String) extends InputRgbImageImporter {
  override def load: Image[RgbPixel] = {
    createImageFromBufferedImage(ImageIO.read(new URL(url)))
  }
}

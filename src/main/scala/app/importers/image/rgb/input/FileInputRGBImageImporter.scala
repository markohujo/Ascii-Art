package app.importers.image.rgb.input

import app.models.Image
import app.models.pixel.RGBPixel

import java.io.File
import javax.imageio.ImageIO

class FileInputRGBImageImporter(path: String) extends InputRGBImageImporter {

  if (!path.endsWith("png") && !path.endsWith("jpg") && !path.endsWith("gif"))
    throw new IllegalArgumentException("Unsupported file extension.")

  override def load: Image[RGBPixel] = {
    createImageFromBufferedImage(ImageIO.read(new File(path)))
  }
}

package importers.image.rgb.input

import app.models.Image
import app.models.pixel.RGBPixel

import java.io.File
import javax.imageio.ImageIO

class FileInputRGBImageImporter(path: String) extends InputRGBImageImporter {

  if (!path.endsWith("png") && !path.endsWith("jpg") && !path.endsWith("gif"))
    throw new IllegalArgumentException("Unsupported file extension.")

  override def load: Image[RGBPixel] = {
    val file = new File(path)
    if (!file.canRead)
      throw new IllegalArgumentException("Cannot open file with the given path")
    createImageFromBufferedImage(ImageIO.read(file))
  }
}

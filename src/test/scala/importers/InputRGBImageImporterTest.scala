package importers

import importers.image.rgb.input.FileInputRGBImageImporter
import org.scalatest.FunSuite

import java.io.File
import javax.imageio.ImageIO

class InputRGBImageImporterTest extends FunSuite {

  test("test loading image") {
    val path = "examples/homer.jpg"
    val importer = new FileInputRGBImageImporter(path)
    val image = importer.load
    val bufferedImage = ImageIO.read(new File(path))

    for (i <- 0 until image.height) {
      for (j <- 0 until image.width) {
        assert(bufferedImage.getRGB(j, i) == image.pixelAt(i, j).value.getRGB)
      }
    }
  }

  test("test loading image - invalid file extension") {
    assertThrows[IllegalArgumentException] {
      new FileInputRGBImageImporter("image.svg")
    }
  }
}

package importers

import importers.image.rgb.input.{FileRgbImageImporter, UrlRgbImageImporter}
import org.scalatest.FunSuite

import java.io.{File, IOException}
import javax.imageio.ImageIO

class InputRgbImageImporterTest extends FunSuite {

  test("test loading image") {
    val path = "examples/homer.jpg"
    val importer = new FileRgbImageImporter(path)
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
      new FileRgbImageImporter("image.svg")
    }
  }

  test("test loading image - no file extension") {
    assertThrows[IllegalArgumentException] {
      new FileRgbImageImporter("idk")
    }
  }
}

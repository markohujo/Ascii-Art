package importers

import importers.image.rgb.input.FileInputRGBImageImporter
import org.scalatest.FunSuite

class InputRGBImageImporterTest extends FunSuite {

  test("createImageFromBufferedImage") {
    ???
  }

  test("invalid file extension") {
    assertThrows[IllegalArgumentException] {
      new FileInputRGBImageImporter("image.svg")
    }
  }
}

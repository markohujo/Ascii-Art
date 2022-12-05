package app.facades


import importers.image.rgb.RGBImageImporter
import org.mockito.Mockito.verify
import org.mockito.MockitoSugar.mock
import org.scalatest.FunSuite

class ImageFacadeTest extends FunSuite {

  private val facade: ImageFacade = new ImageFacade

  test("load image test") {
    val importerMock = mock[RGBImageImporter]
    facade.loadImage(importerMock)
    verify(importerMock).load
  }

  test("setPredefinedTransformationTable invalid input") {
    assertThrows[IllegalArgumentException] {
      facade.setPredefinedTransformationTable("idk")
    }
  }

  test("setPredefinedTransformationTable valid input") {
    facade.setPredefinedTransformationTable("linear")
    facade.setPredefinedTransformationTable("simple")
    facade.setPredefinedTransformationTable("non-linear")
  }

  test("translate image test") {

  }

}

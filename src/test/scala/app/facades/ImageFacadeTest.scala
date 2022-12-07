package app.facades


import app.models.Image
import app.models.pixel.{AsciiPixel, GreyscalePixel, RGBPixel}
import filters.image.greyscale.BrightnessImageFilter
import importers.image.rgb.RGBImageImporter
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.{verify, when}
import org.mockito.MockitoSugar.mock
import org.scalatest.FunSuite

class ImageFacadeTest extends FunSuite {

  private val facade: ImageFacade = new ImageFacade

  test("load image test") {
    val importerMock = mock[RGBImageImporter]
    facade.loadImage(importerMock)
    verify(importerMock).load
  }

  test("setPredefinedTransformationTable test - invalid input - should throw") {
    assertThrows[IllegalArgumentException] {
      facade.setPredefinedTransformationTable("idk")
    }
  }

  test("setPredefinedTransformationTable test - valid input - should succeed") {
    facade.setPredefinedTransformationTable("linear")
    facade.setPredefinedTransformationTable("simple")
    facade.setPredefinedTransformationTable("non-linear")
  }

  test("translate image test") {
    val importerMock = mock[RGBImageImporter]
    val imageMock = mock[Image[RGBPixel]]
    val greyscaleImageMock = mock[Image[GreyscalePixel]]
    val asciiImageMock = mock[Image[AsciiPixel]]

    when(importerMock.load).thenReturn(imageMock)
    when(imageMock.transform(any[RGBPixel => GreyscalePixel])).thenReturn(greyscaleImageMock)
    when(greyscaleImageMock.transform(any[GreyscalePixel => AsciiPixel])).thenReturn(asciiImageMock)

    facade.loadImage(importerMock)
    facade.translateImage()

    verify(importerMock).load
    verify(imageMock).transform(any[RGBPixel => GreyscalePixel])
    verify(greyscaleImageMock).transform(any[GreyscalePixel => AsciiPixel])
  }
}

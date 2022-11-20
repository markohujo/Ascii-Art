package app.console

import app.facades.ImageFacade
import exporters.text.stream.StreamTextExporter
import filters.image.ascii.AsciiImageFilter
import filters.image.grayscale.GrayscaleImageFilter
import importers.image.rgb.RGBImageImporter
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.{times, verify}
import org.mockito.MockitoSugar.mock
import org.scalatest.FunSuite

class ConsoleControllerTest extends FunSuite {

  test("empty args") {
    assertThrows[IllegalArgumentException] {
      new ConsoleController(Array(), new ImageFacade)
    }
  }

  test("no --image argument") {
    assertThrows[IllegalArgumentException] {
      new ConsoleController(Array("--input", "filepath.png", "--output-console", "--invert", "--rotate", "-90"), new ImageFacade)
    }
  }

  test("too many --image argument") {
    assertThrows[IllegalArgumentException] {
      new ConsoleController(Array("--image", "filepath.png", "--image-url", "google.com/image.png", "--output-console", "--invert", "--rotate", "-90"), new ImageFacade)
    }
  }

  test("verify translate image and load image call") {
    val facadeMock = mock[ImageFacade]
    val controller = new ConsoleController(Array("--image", "filepath.png"), facadeMock)

    controller.processUserInput()
    verify(facadeMock).translateImage()
    verify(facadeMock).loadImage(any[RGBImageImporter])
    verify(facadeMock, times(0)).addExporter(any[StreamTextExporter])
    verify(facadeMock, times(0)).addGrayscaleFilter(any[GrayscaleImageFilter])
    verify(facadeMock, times(0)).addAsciiFilter(any[AsciiImageFilter])
  }

  test("verify export call") {
    val facadeMock = mock[ImageFacade]
    val controller = new ConsoleController(Array("--image", "filepath.png", "--output-console"), facadeMock)

    controller.processUserInput()
    verify(facadeMock).translateImage()
    verify(facadeMock).loadImage(any[RGBImageImporter])
    verify(facadeMock).addExporter(any[StreamTextExporter])
    verify(facadeMock, times(0)).addGrayscaleFilter(any[GrayscaleImageFilter])
    verify(facadeMock, times(0)).addAsciiFilter(any[AsciiImageFilter])
  }

  test("verify 2 export calls") {
    val facadeMock = mock[ImageFacade]
    val controller = new ConsoleController(Array("--image", "filepath.png", "--output-console", "--output-file", "outputs/output.txt"), facadeMock)

    controller.processUserInput()
    verify(facadeMock).translateImage()
    verify(facadeMock).loadImage(any[RGBImageImporter])
    verify(facadeMock, times(2)).addExporter(any[StreamTextExporter])
    verify(facadeMock, times(0)).addGrayscaleFilter(any[GrayscaleImageFilter])
    verify(facadeMock, times(0)).addAsciiFilter(any[AsciiImageFilter])
  }

  test("verify 3 export calls") {
    val facadeMock = mock[ImageFacade]
    val controller = new ConsoleController(Array("--image", "filepath.png", "--output-console", "--output-file", "outputs/output.txt", "--output-file", "outputs/another-output.txt"), facadeMock)

    controller.processUserInput()
    verify(facadeMock).translateImage()
    verify(facadeMock).loadImage(any[RGBImageImporter])
    verify(facadeMock, times(3)).addExporter(any[StreamTextExporter])
    verify(facadeMock, times(0)).addGrayscaleFilter(any[GrayscaleImageFilter])
    verify(facadeMock, times(0)).addAsciiFilter(any[AsciiImageFilter])
  }

  test("verify filter calls") {
    val facadeMock = mock[ImageFacade]
    val controller = new ConsoleController(Array("--image", "filepath.png", "--brightness", "+30", "--output-console", "--output-file", "outputs/output.txt", "--rotate", "-90", "--invert"), facadeMock)

    controller.processUserInput()
    verify(facadeMock).translateImage()
    verify(facadeMock).loadImage(any[RGBImageImporter])
    verify(facadeMock, times(2)).addExporter(any[StreamTextExporter])
    verify(facadeMock, times(2)).addGrayscaleFilter(any[GrayscaleImageFilter])
    verify(facadeMock).addAsciiFilter(any[AsciiImageFilter])
  }

}

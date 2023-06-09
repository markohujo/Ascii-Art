package app.console

import app.facades.ImageFacade
import exporters.text.stream.StreamTextExporter
import filters.image.ascii.AsciiImageFilter
import filters.image.greyscale.GreyscaleImageFilter
import importers.image.rgb.RgbImageImporter
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.{times, verify}
import org.mockito.MockitoSugar.mock
import org.scalatest.FunSuite

import java.io.File

class ConsoleUserInputHandlerTest extends FunSuite {

  test("empty args") {
    assertThrows[IllegalArgumentException] {
      new ConsoleUserInputHandler(List(), new ImageFacade)
    }
  }

  test("no --image argument") {
    assertThrows[IllegalArgumentException] {
      new ConsoleUserInputHandler(List("--input", "filepath.png", "--output-console", "--invert", "--rotate", "-90"), new ImageFacade)
    }
  }

  test("too many --image argument") {
    assertThrows[IllegalArgumentException] {
      new ConsoleUserInputHandler(List("--image", "filepath.png", "--image-url", "google.com/image.png", "--output-console", "--invert", "--rotate", "-90"), new ImageFacade)
    }
  }

  test("too many transformation tables") {
    assertThrows[IllegalArgumentException] {
      new ConsoleUserInputHandler(List("--table", "linear", "--custom-table", " .:-=+*#%@", "--image", "filepath.png", "--output-console"), new ImageFacade)
    }
    assertThrows[IllegalArgumentException] {
      new ConsoleUserInputHandler(List("--table", "linear", "--table", "nonlinear", "--image", "filepath.png", "--output-console"), new ImageFacade)
    }
    assertThrows[IllegalArgumentException] {
      new ConsoleUserInputHandler(List("--custom-table", " @@@###   ***&&&   ---+++ ", "--custom-table", " .:-=+*#%@", "--image", "filepath.png", "--output-console"), new ImageFacade)
    }
  }

  test("verify translate image and load image call") {
    val facadeMock = mock[ImageFacade]
    val inputHandler = new ConsoleUserInputHandler(List("--image", "filepath.png"), facadeMock)

    inputHandler.processUserInput()

    verify(facadeMock).translateImage()
    verify(facadeMock).loadImage(any[RgbImageImporter])
    verify(facadeMock, times(0)).addExporter(any[StreamTextExporter])
    verify(facadeMock, times(0)).addGrayscaleFilter(any[GreyscaleImageFilter])
    verify(facadeMock, times(0)).addAsciiFilter(any[AsciiImageFilter])
  }

  test("verify export call") {
    val facadeMock = mock[ImageFacade]
    val inputHandler = new ConsoleUserInputHandler(List("--image", "filepath.png", "--output-console"), facadeMock)

    inputHandler.processUserInput()

    verify(facadeMock).translateImage()
    verify(facadeMock).loadImage(any[RgbImageImporter])
    verify(facadeMock).addExporter(any[StreamTextExporter])
    verify(facadeMock, times(0)).addGrayscaleFilter(any[GreyscaleImageFilter])
    verify(facadeMock, times(0)).addAsciiFilter(any[AsciiImageFilter])
  }

  test("verify 2 export calls") {
    val outputPath = "tmp-output.txt"

    val facadeMock = mock[ImageFacade]
    val inputHandler = new ConsoleUserInputHandler(List("--image", "filepath.png", "--output-console", "--output-file", outputPath), facadeMock)

    inputHandler.processUserInput()

    verify(facadeMock).translateImage()
    verify(facadeMock).loadImage(any[RgbImageImporter])
    verify(facadeMock, times(2)).addExporter(any[StreamTextExporter])
    verify(facadeMock, times(0)).addGrayscaleFilter(any[GreyscaleImageFilter])
    verify(facadeMock, times(0)).addAsciiFilter(any[AsciiImageFilter])

    clean(outputPath)
  }

  test("verify 3 export calls") {
    val outputPath1 = "tmp-output1.txt"
    val outputPath2 = "tmp-output2.txt"

    val facadeMock = mock[ImageFacade]
    val inputHandler = new ConsoleUserInputHandler(List("--image", "filepath.png", "--output-console", "--output-file", outputPath1, "--output-file", outputPath2), facadeMock)

    inputHandler.processUserInput()

    verify(facadeMock).translateImage()
    verify(facadeMock).loadImage(any[RgbImageImporter])
    verify(facadeMock, times(3)).addExporter(any[StreamTextExporter])
    verify(facadeMock, times(0)).addGrayscaleFilter(any[GreyscaleImageFilter])
    verify(facadeMock, times(0)).addAsciiFilter(any[AsciiImageFilter])

    clean(outputPath1)
    clean(outputPath2)
  }

  test("verify filter calls") {
    val outputPath = "tmp-output.txt"

    val facadeMock = mock[ImageFacade]
    val inputHandler = new ConsoleUserInputHandler(List("--image", "filepath.png", "--brightness", "+30", "--output-console", "--output-file", outputPath, "--rotate", "-90", "--invert"), facadeMock)

    inputHandler.processUserInput()

    verify(facadeMock).translateImage()
    verify(facadeMock).loadImage(any[RgbImageImporter])
    verify(facadeMock, times(2)).addExporter(any[StreamTextExporter])
    verify(facadeMock, times(2)).addGrayscaleFilter(any[GreyscaleImageFilter])
    verify(facadeMock).addAsciiFilter(any[AsciiImageFilter])

    clean(outputPath)
  }

  test("verify setPredefinedTable call") {
    val facadeMock = mock[ImageFacade]
    val inputHandler = new ConsoleUserInputHandler(List("--image", "filepath.png", "--table", "linear", "--output-console"), facadeMock)

    inputHandler.processUserInput()

    verify(facadeMock).translateImage()
    verify(facadeMock).loadImage(any[RgbImageImporter])
    verify(facadeMock).addExporter(any[StreamTextExporter])
    verify(facadeMock).setPredefinedTransformationTable("linear")
  }

  test("verify setCustomTable call") {
    val facadeMock = mock[ImageFacade]
    val inputHandler = new ConsoleUserInputHandler(List("--image", "filepath.png", "--custom-table", " .:-=+*#%@", "--output-console"), facadeMock)

    inputHandler.processUserInput()

    verify(facadeMock).translateImage()
    verify(facadeMock).loadImage(any[RgbImageImporter])
    verify(facadeMock).addExporter(any[StreamTextExporter])
    verify(facadeMock).setCustomTransformationTable(" .:-=+*#%@")
  }

  private def clean(path: String): Boolean = new File(path).delete()
}

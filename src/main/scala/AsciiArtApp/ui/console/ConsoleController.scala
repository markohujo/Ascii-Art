package AsciiArtApp.ui.console

import AsciiArtApp.Executor
import AsciiArtApp.exporters.text.stream.{ConsoleTextExporter, FileTextExporter}
import AsciiArtApp.filters.image.ascii.{RotateImageFilter, ScaleImageFilter}
import AsciiArtApp.filters.image.grayscale.{BrightnessImageFilter, InvertImageFilter}
import AsciiArtApp.importers.image.rgb.input.{FileInputRGBImageImporter, URLInputRGBImageImporter}
import AsciiArtApp.importers.image.rgb.random.RandomRGBImageImporter
import AsciiArtApp.ui.Controller

import scala.util.matching.Regex

class ConsoleController extends Controller[Array[String]] {

  private val randomImportPattern: Regex = "^--image-random$".r
  private val urlImportPattern: Regex = "^--image-url\\s+(.*)$".r
  private val fileImportPattern: Regex = "^--image\\s+(.*)$".r
  private val brightnessPattern: Regex = "^--brightness\\s+(.*)$".r
  private val invertPattern: Regex = "^--invert$".r
  private val rotatePattern: Regex = "^--rotate\\s+(.*)$".r
  private val scalePattern: Regex = "^--scale\\s+(.*)$".r
  private val outputConsolePattern: Regex = "^--output-console$".r
  private val outputFilePattern: Regex = "^--output-file\\s+(.*)$".r

  override def processUserInput(input: Array[String]): Unit = {
    if (input.isEmpty)
      throw new IllegalArgumentException("No arguments were given by the user.")

    if (input.count(_.startsWith("--image")) == 0)
      throw new IllegalArgumentException("No --image argument specified.")

    if (input.count(_.startsWith("--image")) > 1)
      throw new IllegalArgumentException("More than 1 --image* argument specified.")

    parseInput(input).foreach(prepareForExecution)
  }

  private def parseInput(input: Array[String]): Seq[String] = {
    var result = Seq.empty[String]
    var argument: Option[String] = Option.empty

    for (arg <- input) {
      if (arg.startsWith("--")) {
        if (argument.isDefined)
          result = result.appended(argument.get)
        argument = Option.apply(arg)
      }
      else {
        argument = Option.apply(argument.get.appendedAll(" ").appendedAll(arg))
      }
    }

    result.appended(argument.get)
  }

  private def prepareForExecution(argument: String): Unit = {
    argument match {
      case randomImportPattern() => Executor.setImporter(new RandomRGBImageImporter)
      case urlImportPattern(path) => Executor.setImporter(new URLInputRGBImageImporter(path))
      case fileImportPattern(path) => Executor.setImporter(new FileInputRGBImageImporter(path))
      case brightnessPattern(value) => Executor.addGrayscaleFilter(new BrightnessImageFilter(value.toInt))
      case invertPattern() => Executor.addGrayscaleFilter(new InvertImageFilter)
      case rotatePattern(value) => Executor.addAsciiFilter(new RotateImageFilter(value.toInt))
      case scalePattern(value) => Executor.addAsciiFilter(new ScaleImageFilter(value.toDouble))
      case outputConsolePattern() => Executor.addExporter(new ConsoleTextExporter)
      case outputFilePattern(path) => Executor.addExporter(new FileTextExporter(path))
    }
  }
}

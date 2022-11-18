package app.ui.console

import app.exporters.text.stream.{ConsoleTextExporter, FileTextExporter}
import app.facades.ImageFacade
import app.filters.image.ascii.RotateImageFilter
import app.filters.image.grayscale.{BrightnessImageFilter, InvertImageFilter}
import app.importers.image.rgb.input.{FileInputRGBImageImporter, URLInputRGBImageImporter}
import app.importers.image.rgb.random.RandomRGBImageImporter
import app.ui.Controller

import scala.util.matching.Regex

class ConsoleController(input: Seq[String], facade: ImageFacade) extends Controller {

  private val randomImportPattern: Regex = "^--image-random$".r
  private val urlImportPattern: Regex = "^--image-url\\s+(.*)$".r
  private val fileImportPattern: Regex = "^--image\\s+(.*)$".r
  private val brightnessPattern: Regex = "^--brightness\\s+(.*)$".r
  private val invertPattern: Regex = "^--invert$".r
  private val rotatePattern: Regex = "^--rotate\\s+(.*)$".r
  private val outputConsolePattern: Regex = "^--output-console$".r
  private val outputFilePattern: Regex = "^--output-file\\s+(.*)$".r

  override def processUserInput(): Unit = {
    if (input.isEmpty)
      throw new IllegalArgumentException("No arguments were given by the user.")

    if (input.count(_.startsWith("--image")) == 0)
      throw new IllegalArgumentException("No --image argument specified.")

    if (input.count(_.startsWith("--image")) > 1)
      throw new IllegalArgumentException("More than 1 --image* argument specified.")

    parseInput().foreach(processArgument)
    facade.applyFilters()
    facade.exportImage()
  }

  /**
   * Parses user input from the command line to a sequence of arguments
   * @return Sequence of arguments
   */
  private def parseInput(): Seq[String] = {
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

  /**
   * Processes each command line argument and calls corresponding facade method
   * @param argument - argument to process
   */
  private def processArgument(argument: String): Unit = {
    argument match {
      case randomImportPattern() => facade.loadImage(new RandomRGBImageImporter)
      case urlImportPattern(path) => facade.loadImage(new URLInputRGBImageImporter(path))
      case fileImportPattern(path) => facade.loadImage(new FileInputRGBImageImporter(path))
      case brightnessPattern(value) => facade.addGrayscaleFilter(new BrightnessImageFilter(value.toInt))
      case invertPattern() => facade.addGrayscaleFilter(new InvertImageFilter)
      case rotatePattern(value) => facade.addAsciiFilter(new RotateImageFilter(value.toInt))
      case outputConsolePattern() => facade.addExporter(new ConsoleTextExporter)
      case outputFilePattern(path) => facade.addExporter(new FileTextExporter(path))
      case _ => throw new IllegalArgumentException("Invalid arguments")
    }
  }
}

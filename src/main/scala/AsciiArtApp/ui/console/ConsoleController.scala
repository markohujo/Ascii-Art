package AsciiArtApp.ui.console

import AsciiArtApp.Executor
import AsciiArtApp.exporters.text.stream.{ConsoleTextExporter, FileTextExporter}
import AsciiArtApp.filters.image.ascii.{RotateImageFilter, ScaleImageFilter}
import AsciiArtApp.filters.image.grayscale.{BrightnessImageFilter, InvertImageFilter}
import AsciiArtApp.importers.image.rgb.input.{FileInputRGBImageImporter, URLInputRGBImageImporter}
import AsciiArtApp.importers.image.rgb.random.RandomRGBImageImporter
import AsciiArtApp.ui.Controller

class ConsoleController extends Controller[Array[String]] {

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
    println(argument)
    val argumentSplit = argument.split(" ")
    var argumentValue: String = null
    if (argumentSplit.length == 2)
      argumentValue = argumentSplit(1)

    if (argument.startsWith("--image-random"))
      Executor.setImporter(new RandomRGBImageImporter)

    else if (argument.startsWith("--image-url"))
      Executor.setImporter(new URLInputRGBImageImporter(argumentValue))

    else if (argument.startsWith("--image"))
      Executor.setImporter(new FileInputRGBImageImporter(argumentValue))

    else if (argument.startsWith("--brightness"))
      Executor.addGrayscaleFilter(new BrightnessImageFilter(argumentValue.toInt))

    else if (argument.startsWith("--invert"))
      Executor.addGrayscaleFilter(new InvertImageFilter)

    else if (argument.startsWith("--rotate"))
      Executor.addAsciiFilter(new RotateImageFilter(argumentValue.toInt))

    else if (argument.startsWith("--scale"))
      Executor.addAsciiFilter(new ScaleImageFilter(argumentValue.toDouble))

    else if (argument.startsWith("--output-console"))
      Executor.addExporter(new ConsoleTextExporter)

    else if (argument.startsWith("--output-file"))
      Executor.addExporter(new FileTextExporter(argumentValue))
  }
}

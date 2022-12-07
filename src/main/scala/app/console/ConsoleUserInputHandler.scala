package app.console

import app.facades.ImageFacade
import exporters.text.stream.{ConsoleTextExporter, FileTextExporter}
import filters.image.ascii.RotateImageFilter
import filters.image.greyscale.{BrightnessImageFilter, InvertImageFilter}
import importers.image.rgb.input.{FileRgbImageImporter, UrlRgbImageImporter}
import importers.image.rgb.random.RandomRgbImageImporter

/**
 * Handles user input from the console (command line arguments)
 *
 * @param input       sequence of command line arguments
 * @param imageFacade image facade for business logic
 *
 * @throws IllegalArgumentException if invalid command line arguments are specified
 */
class ConsoleUserInputHandler(private var input: List[String], imageFacade: ImageFacade) extends UserInputHandler {

  if (input.isEmpty)
    throw new IllegalArgumentException("No arguments were specified.")

  if (input.count(_.startsWith("--image")) != 1)
    throw new IllegalArgumentException("Exactly 1 --image* argument must be specified.")

  if (input.count(_.endsWith("-table")) > 1)
    throw new IllegalArgumentException("More than one transformation table was specified.")

  /**
   * Processes command line arguments and translates image
   *
   * @throws IllegalArgumentException if no command line arguments were specified
   * @throws IllegalArgumentException if no --image* command line argument was specified
   * @throws IllegalArgumentException if more than 1 --image* command line argument was specified
   */
  override def processUserInput(): Unit = {
    processArguments()
    imageFacade.translateImage()
  }

  /**
   * Processes command line arguments and calls corresponding facade method
   *
   * @throws IllegalArgumentException if invalid arguments were specified
   */
  private def processArguments(): Unit = {
    while (input.nonEmpty) {
      input match {
        case "--image" :: path :: tail =>
          imageFacade.loadImage(new FileRgbImageImporter(path))
          input = tail
        case "--image-url" :: path :: tail =>
          imageFacade.loadImage(new UrlRgbImageImporter(path))
          input = tail
        case "--image--random" :: tail =>
          imageFacade.loadImage(new RandomRgbImageImporter)
          input = tail
        case "--brightness" :: value :: tail =>
          imageFacade.addGrayscaleFilter(new BrightnessImageFilter(value.toInt))
          input = tail
        case "--invert" :: tail =>
          imageFacade.addGrayscaleFilter(new InvertImageFilter)
          input = tail
        case "--rotate" :: value :: tail =>
          imageFacade.addAsciiFilter(new RotateImageFilter(value.toInt))
          input = tail
        case "--output-console" :: tail =>
          imageFacade.addExporter(new ConsoleTextExporter)
          input = tail
        case "--output-file" :: path :: tail =>
          imageFacade.addExporter(new FileTextExporter(path))
          input = tail
        case "--table" :: tableName :: tail =>
          imageFacade.setPredefinedTransformationTable(tableName)
          input = tail
        case "--custom-table" :: characters :: tail =>
          imageFacade.setCustomTransformationTable(characters)
          input = tail
        case _ => throw new IllegalArgumentException("Invalid argument")
      }
    }
  }
}

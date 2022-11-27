package app.console

import app.facades.ImageFacade
import exporters.text.stream.{ConsoleTextExporter, FileTextExporter}
import filters.image.ascii.RotateImageFilter
import filters.image.grayscale.{BrightnessImageFilter, InvertImageFilter}
import importers.image.rgb.input.{FileInputRGBImageImporter, URLInputRGBImageImporter}
import importers.image.rgb.random.RandomRGBImageImporter

/**
 * Handles user input from the console (command line arguments)
 *
 * @param input       sequence of command line arguments
 * @param imageFacade image facade for business logic
 */
class ConsoleUserInputHandler(input: List[String], imageFacade: ImageFacade) extends UserInputHandler {

  if (input.isEmpty)
    throw new IllegalArgumentException("No arguments were specified.")

  if (input.count(_.startsWith("--image")) == 0)
    throw new IllegalArgumentException("No --image* argument was specified.")

  if (input.count(_.startsWith("--image")) > 1)
    throw new IllegalArgumentException("More than 1 --image* argument was specified.")

  /**
   * TODO
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
   * TODO
   * Processes arguments and calls corresponding facade method
   *
   * @throws IllegalArgumentException if invalid arguments were specified
   */
  private def processArguments(): Unit = {
    var inputIter: List[String] = input

    while (inputIter.nonEmpty) {
      inputIter match {
        case "--image" :: path :: tail =>
          imageFacade.loadImage(new FileInputRGBImageImporter(path))
          inputIter = tail
        case "--image-url" :: path :: tail =>
          imageFacade.loadImage(new URLInputRGBImageImporter(path))
          inputIter = tail
        case "--image--random" :: tail =>
          imageFacade.loadImage(new RandomRGBImageImporter)
          inputIter = tail
        case "--brightness" :: value :: tail =>
          imageFacade.addGrayscaleFilter(new BrightnessImageFilter(value.toInt))
          inputIter = tail
        case "--invert" :: tail =>
          imageFacade.addGrayscaleFilter(new InvertImageFilter)
          inputIter = tail
        case "--rotate" :: value :: tail =>
          imageFacade.addAsciiFilter(new RotateImageFilter(value.toInt))
          inputIter = tail
        case "--output-console" :: tail =>
          imageFacade.addExporter(new ConsoleTextExporter)
          inputIter = tail
        case "--output-file" :: path :: tail =>
          imageFacade.addExporter(new FileTextExporter(path))
          inputIter = tail
        case _ => throw new IllegalArgumentException("Invalid argument")
      }
    }
  }
}

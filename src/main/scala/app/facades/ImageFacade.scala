package app.facades

import app.models.Image
import app.models.pixel.{AsciiPixel, GreyscalePixel, RgbPixel}
import converters.imageToText.AsciiImageToTextConverter
import converters.pixel.{GreyscaleToAsciiConverter, LinearGreyscaleToAsciiConverter, NonLinearGreyscaleToAsciiConverter, RgbToGrayscaleConverter}
import exporters.text.stream.StreamTextExporter
import filters.image.ascii.AsciiImageFilter
import filters.image.greyscale.GreyscaleImageFilter
import importers.image.rgb.RgbImageImporter
import utils.Constants

class ImageFacade {

  private val RGBToGrayscaleConverter = new RgbToGrayscaleConverter
  private var grayscaleToAsciiConverter: GreyscaleToAsciiConverter = new LinearGreyscaleToAsciiConverter
  private val asciiToTextConverter = new AsciiImageToTextConverter

  private var image: Option[Image[RgbPixel]] = None
  private var greyscaleImage: Option[Image[GreyscalePixel]] = None
  private var asciiImage: Option[Image[AsciiPixel]] = None
  private var grayscaleFilters: Seq[GreyscaleImageFilter] = Seq.empty
  private var asciiFilters: Seq[AsciiImageFilter] = Seq.empty
  private var exporters: Seq[StreamTextExporter] = Seq.empty

  /**
   * Loads an image using the given image importer
   *
   * @param importer - image importer
   */
  def loadImage(importer: RgbImageImporter): Unit = image = Some(importer.load)

  def addGrayscaleFilter(filter: GreyscaleImageFilter): Unit =
    grayscaleFilters = grayscaleFilters.appended(filter)

  def addAsciiFilter(filter: AsciiImageFilter): Unit =
    asciiFilters = asciiFilters.appended(filter)

  def addExporter(exporter: StreamTextExporter): Unit =
    exporters = exporters.appended(exporter)

  def setPredefinedTransformationTable(tableName: String): Unit = {
    tableName match {
      case "linear" => grayscaleToAsciiConverter = new LinearGreyscaleToAsciiConverter
      case "non-linear" | "nonlinear" => grayscaleToAsciiConverter = new NonLinearGreyscaleToAsciiConverter
      case "simplified" | "simple" =>
        grayscaleToAsciiConverter = new LinearGreyscaleToAsciiConverter(Constants.DefaultSimplifiedTransformationTable)
      case _ => throw new IllegalArgumentException("Undefined transformation table name \"" + tableName + "\".")
    }
  }

  def setCustomTransformationTable(characters: String): Unit =
    grayscaleToAsciiConverter = new LinearGreyscaleToAsciiConverter(characters)

  /**
   * Translates image by converting it from rgb to ascii, applying filters and exporting it
   */
  def translateImage(): Unit = {
    applyFilters()
    exportImage()
  }

  /**
   * Converts from RGB image to grayscale image
   * Applies grayscale filter
   * Converts from grayscale image to ascii image
   * Applies ascii filters
   */
  private def applyFilters(): Unit = {
    applyGrayscaleFilters()
    applyAsciiFilters()
  }

  private def applyGrayscaleFilters(): Unit = {
    if (image.isEmpty)
      throw new Exception("Cannot apply filters to an empty image")
    greyscaleImage = Some(image.get.transform(RGBToGrayscaleConverter.convert))
    grayscaleFilters.foreach(filter => greyscaleImage = Some(filter.apply(greyscaleImage.get)))
  }

  private def applyAsciiFilters(): Unit = {
    if (greyscaleImage.isEmpty)
      throw new Exception("Cannot apply filters to an empty image")
    asciiImage = Some(greyscaleImage.get.transform(grayscaleToAsciiConverter.convert))
    asciiFilters.foreach(filter => asciiImage = Some(filter.apply(asciiImage.get)))
  }

  /**
   * Exports the image to all added save targets
   */
  private def exportImage(): Unit = {
    if (asciiImage.isEmpty)
      throw new Exception("Cannot export an empty image")
    val imageText = asciiToTextConverter.convert(asciiImage.get)
    exporters.foreach(exporter => exporter.save(imageText))
  }
}

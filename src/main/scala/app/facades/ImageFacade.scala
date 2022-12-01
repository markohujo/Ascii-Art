package app.facades

import app.models.Image
import app.models.pixel.{AsciiPixel, GrayscalePixel, RGBPixel}
import converters.imageToText.AsciiImageToTextConverter
import converters.pixel.{GrayscaleToAsciiConverter, LinearGrayscaleToAsciiConverter, NonLinearGrayscaleToAsciiConverter, RGBToGrayscaleConverter}
import exporters.text.stream.StreamTextExporter
import filters.image.ascii.AsciiImageFilter
import filters.image.grayscale.GrayscaleImageFilter
import importers.image.rgb.RGBImageImporter

class ImageFacade {

  private val RGBToGrayscaleConverter = new RGBToGrayscaleConverter
  private val asciiToTextConverter = new AsciiImageToTextConverter

  private val defaultTransformationTable =
    "$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\\|()1{}[]?-_+~<>i!lI;:,\"^`'. "
  private var grayscaleToAsciiConverter: GrayscaleToAsciiConverter = _

  private var image: Image[RGBPixel] = _
  private var grayscaleImage: Image[GrayscalePixel] = _
  private var asciiImage: Image[AsciiPixel] = _
  private var grayscaleFilters: Seq[GrayscaleImageFilter] = Seq.empty
  private var asciiFilters: Seq[AsciiImageFilter] = Seq.empty
  private var exporters: Seq[StreamTextExporter] = Seq.empty

  /**
   * Loads an image using the given image importer
   *
   * @param importer - image importer
   */
  def loadImage(importer: RGBImageImporter): Unit = image = importer.load

  def addGrayscaleFilter(filter: GrayscaleImageFilter): Unit =
    grayscaleFilters = grayscaleFilters.appended(filter)

  def addAsciiFilter(filter: AsciiImageFilter): Unit =
    asciiFilters = asciiFilters.appended(filter)

  def addExporter(exporter: StreamTextExporter): Unit =
    exporters = exporters.appended(exporter)

  def setPredefinedTable(tableName: String): Unit = {
    tableName match {
      case "linear" => grayscaleToAsciiConverter = new LinearGrayscaleToAsciiConverter
      case "non-linear" | "nonlinear" => grayscaleToAsciiConverter = new NonLinearGrayscaleToAsciiConverter
      case _ => throw new IllegalArgumentException("Undefined transformation table name \"" + tableName + "\".")
    }
  }

  def setTransformationTable(characters: String): Unit =
    grayscaleToAsciiConverter = new LinearGrayscaleToAsciiConverter(characters)

  /**
   * Translates image by converting it from rgb to ascii, applying filters and exporting it
   */
  def translateImage(): Unit = {
    if (grayscaleToAsciiConverter == null)
      grayscaleToAsciiConverter = new LinearGrayscaleToAsciiConverter
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
    grayscaleImage = image.transform(RGBToGrayscaleConverter.convert)
    grayscaleFilters.foreach(filter => grayscaleImage = filter.apply(grayscaleImage))
  }

  private def applyAsciiFilters(): Unit = {
    asciiImage = grayscaleImage.transform(grayscaleToAsciiConverter.convert)
    asciiFilters.foreach(filter => asciiImage = filter.apply(asciiImage))
  }

  /**
   * Exports the image to all added save targets
   */
  private def exportImage(): Unit = {
    val imageText = asciiToTextConverter.convert(asciiImage)
    exporters.foreach(exporter => exporter.save(imageText))
  }

}

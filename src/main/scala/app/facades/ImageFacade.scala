package app.facades

import app.converters.pixel.{GrayscaleToAsciiConverter, RGBToGrayscaleConverter}
import app.converters.toText.AsciiImageToTextConverter
import app.exporters.text.stream.StreamTextExporter
import app.filters.image.ascii.AsciiImageFilter
import app.filters.image.grayscale.GrayscaleImageFilter
import app.importers.image.rgb.RGBImageImporter
import app.models.Image
import app.models.pixel.{AsciiPixel, GrayscalePixel, RGBPixel}

class ImageFacade {

  private var image: Image[RGBPixel] = _
  private var grayscaleImage: Image[GrayscalePixel] = _
  private var asciiImage: Image[AsciiPixel] = _
  private var grayscaleFilters: Seq[GrayscaleImageFilter] = Seq.empty
  private var asciiFilters: Seq[AsciiImageFilter] = Seq.empty
  private var exporters: Seq[StreamTextExporter] = Seq.empty
  private val RGBToGrayscaleConverter = new RGBToGrayscaleConverter
  private val grayscaleToAsciiConverter = new GrayscaleToAsciiConverter
  private val asciiToTextConverter = new AsciiImageToTextConverter

  /**
   * Loads an image using the given image importer
   *
   * @param importer - image importer
   */
  def loadImage(importer: RGBImageImporter): Unit = image = importer.load

  /**
   * Converts from RGB image to grayscale image
   * Applies grayscale filter
   * Converts from grayscale image to ascii image
   * Applies ascii filters
   */
  def applyFilters(): Unit = {
    applyGrayscaleFilters()
    applyAsciiFilters()
  }

  /**
   * Exports the image to all added save target
   */
  def exportImage(): Unit = {
    exporters.foreach(exporter => {
      exporter.save(asciiToTextConverter.convert(asciiImage))
      exporter.close()
    })
  }

  def addGrayscaleFilter(filter: GrayscaleImageFilter): Unit = grayscaleFilters = grayscaleFilters.appended(filter)

  def addAsciiFilter(filter: AsciiImageFilter): Unit = asciiFilters = asciiFilters.appended(filter)

  def addExporter(exporter: StreamTextExporter): Unit = exporters = exporters.appended(exporter)

  private def applyGrayscaleFilters(): Unit = {
    grayscaleImage = image.transform(RGBToGrayscaleConverter.convert)
    grayscaleFilters.foreach(filter => grayscaleImage = filter.apply(grayscaleImage))
  }

  private def applyAsciiFilters(): Unit = {
    asciiImage = grayscaleImage.transform(grayscaleToAsciiConverter.convert)
    asciiFilters.foreach(filter => asciiImage = filter.apply(asciiImage))
  }

}

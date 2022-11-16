package AsciiArtApp

import AsciiArtApp.converters.pixel.{GrayscaleToAsciiConverter, RGBToGrayscaleConverter}
import AsciiArtApp.converters.toText.AsciiImageToTextConverter
import AsciiArtApp.exporters.text.TextExporter
import AsciiArtApp.filters.image.ascii.AsciiImageFilter
import AsciiArtApp.filters.image.grayscale.GrayscaleImageFilter
import AsciiArtApp.importers.image.rgb.RGBImageImporter

object Executor {

  private var importer: RGBImageImporter = _
  private var grayscaleFilters: Seq[GrayscaleImageFilter] = Seq.empty
  private var asciiFilters: Seq[AsciiImageFilter] = Seq.empty
  private var exporters: Seq[TextExporter] = Seq.empty

  def run(): Unit = {
    val image = importer.load

    var grayscaleImage = image.transform(new RGBToGrayscaleConverter().convert)
    grayscaleFilters.foreach(filter => grayscaleImage = filter.filter(grayscaleImage))

    var asciiImage = grayscaleImage.transform(new GrayscaleToAsciiConverter().convert)
    asciiFilters.foreach(filter => asciiImage = filter.filter(asciiImage))

    val outputText = new AsciiImageToTextConverter().convert(asciiImage)
    exporters.foreach(exporter => exporter.export(outputText))
  }

  def setImporter(importer: RGBImageImporter): Unit = this.importer = importer

  def addGrayscaleFilter(filter: GrayscaleImageFilter): Unit = grayscaleFilters = grayscaleFilters.appended(filter)

  def addAsciiFilter(filter: AsciiImageFilter): Unit = asciiFilters = asciiFilters.appended(filter)

  def addExporter(filter: TextExporter): Unit = exporters = exporters.appended(filter)
}

package AsciiArtApp.importers.image

import AsciiArtApp.importers.Importer
import AsciiArtApp.models.Image
import AsciiArtApp.models.pixel.Pixel

trait ImageImporter[T <: Pixel] extends Importer[Image[T]] {}

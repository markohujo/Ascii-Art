package app.importers.image

import app.importers.Importer
import app.models.Image
import app.models.pixel.Pixel

trait ImageImporter[T <: Pixel] extends Importer[Image[T]] {}

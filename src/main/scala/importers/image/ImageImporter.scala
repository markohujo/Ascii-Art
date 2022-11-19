package importers.image

import app.models.Image
import app.models.pixel.Pixel
import importers.Importer

trait ImageImporter[T <: Pixel] extends Importer[Image[T]] {}

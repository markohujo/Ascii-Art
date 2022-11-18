package app.filters.image

import app.filters.Filter
import app.models.Image
import app.models.pixel.Pixel

trait ImageFilter[T <: Pixel] extends Filter[Image[T]] {}

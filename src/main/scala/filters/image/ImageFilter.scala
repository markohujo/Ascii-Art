package filters.image

import app.models.Image
import app.models.pixel.Pixel
import filters.Filter

trait ImageFilter[T <: Pixel] extends Filter[Image[T]] {}

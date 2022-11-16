package AsciiArtApp.filters.image

import AsciiArtApp.filters.Filter
import AsciiArtApp.models.Image
import AsciiArtApp.models.pixel.Pixel

trait ImageFilter[T <: Pixel] extends Filter[Image[T]] {}

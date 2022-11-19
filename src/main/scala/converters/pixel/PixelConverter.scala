package converters.pixel

import app.models.pixel.Pixel
import converters.Converter

trait PixelConverter[T <: Pixel, S <: Pixel] extends Converter[T, S] {}

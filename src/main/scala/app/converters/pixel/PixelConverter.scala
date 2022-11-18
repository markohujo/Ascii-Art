package app.converters.pixel

import app.converters.Converter
import app.models.pixel.Pixel

trait PixelConverter[T <: Pixel, S <: Pixel] extends Converter[T, S] {}

package AsciiArtApp.converters.pixel

import AsciiArtApp.converters.Converter
import AsciiArtApp.models.pixel.Pixel

trait PixelConverter[T <: Pixel, S <: Pixel] extends Converter[T, S] {}

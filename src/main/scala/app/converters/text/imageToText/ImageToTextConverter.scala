package app.converters.text.imageToText

import app.converters.text.TextConverter
import app.models.Image
import app.models.pixel.Pixel

trait ImageToTextConverter[T <: Pixel] extends TextConverter[Image[T]] {}

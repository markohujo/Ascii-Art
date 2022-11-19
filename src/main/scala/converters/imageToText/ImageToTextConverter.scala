package converters.imageToText

import app.models.Image
import app.models.pixel.Pixel
import converters.Converter

trait ImageToTextConverter[T <: Pixel] extends Converter[Image[T], String] {}

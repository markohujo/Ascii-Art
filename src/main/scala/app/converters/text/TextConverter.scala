package app.converters.text

import app.converters.Converter

trait TextConverter[T] extends Converter[T, String] {}

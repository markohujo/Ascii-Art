package app.converters.toText

import app.converters.Converter

trait ToTextConverter[T] extends Converter[T, String] {}

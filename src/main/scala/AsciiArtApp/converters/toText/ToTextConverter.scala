package AsciiArtApp.converters.toText

import AsciiArtApp.converters.Converter

trait ToTextConverter[T] extends Converter[T, String] {}

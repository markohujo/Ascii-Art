package AsciiArtApp.converters

/**
 * @tparam T convert from
 * @tparam S convert to
 */
trait Converter[T, S] {
  def convert(from: T): S
}

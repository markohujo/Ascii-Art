package converters

/**
 * @tparam T convert from
 * @tparam S convert to
 */
trait Converter[T, S] {
  /**
   * Converts the given item of type T to an item of type S
   *
   * @param item item to convert
   * @return Converted item of type S
   */
  def convert(item: T): S
}

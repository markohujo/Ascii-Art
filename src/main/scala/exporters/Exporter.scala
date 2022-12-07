package exporters

/**
 * @tparam T type of object that is exported by this exporter
 */
trait Exporter[T] {
  /**
   * Exports the given item
   *
   * @param item item to export
   */
  def save(item: T): Unit
}

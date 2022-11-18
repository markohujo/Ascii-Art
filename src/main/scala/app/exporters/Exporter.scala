package app.exporters

trait Exporter[T] {
  /**
   * Exports the given item
   * @param item item to export
   */
  def save(item: T): Unit
}

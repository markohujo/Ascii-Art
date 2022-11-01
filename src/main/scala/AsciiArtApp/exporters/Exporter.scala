package AsciiArtApp.exporters

trait Exporter[T] {
  /**
   * Exports the given item
   * @param item item to export
   */
  def export(item: T): Unit
}
